package ir.example.app.util

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Handler
import android.os.Message
import ir.example.app.ui.MainActivity
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*


class BluetoothHelper(var activity: MainActivity) {

    private val MY_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    var BA: BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    var mSocket: BluetoothSocket? = null
    val RECIEVE_MESSAGE = 1 // Status

    // handler is companion to prevent leaking ref.
    companion object {
        lateinit var h: Handler
    }
    init {

        h = object : Handler() {
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    RECIEVE_MESSAGE -> {
                        val readBuf = msg.obj as ByteArray
                        activity.showMessage("message is ${readBuf.toList()}")

                        Timber.i("message is ${readBuf.toList()}")
                        }
                    }
                }
            }
        }

    fun connect(device: BluetoothDevice) {
        Timber.i("BluetoothHelper: connect() start")
        try {
            mSocket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID)
            Timber.i("BluetoothHelper: connect() socket created successfully")

        } catch (e1: IOException) {
            activity.showMessage("error = $e1")
            Timber.i("BluetoothHelper: connect() socket creation error $e1")
        }

        try {
            mSocket?.connect()
            Timber.i("BluetoothHelper: connect() successfully connected")
            activity.showMessage("connected to ${device.name}")
            try {
                val mConnectedThread = ConnectedThread(mSocket!!)
                mConnectedThread.start()
            } catch (e: Exception) {
                Timber.i("BluetoothHelper: runServer() error while reading next data $e")
                activity.showMessage("Socket closed please reconnect the devices via the list.")
            }
            Timber.i("BluetoothHelper: connect() successfully sent message")
        } catch (e: IOException) {
            Timber.i("BluetoothHelper: connect() socket connecting error $e")
            activity.showMessage("error = $e")
        }
    }

    fun disconnect() {
        try {
            mSocket?.close()
        } catch (e2: IOException) {

        }
    }

    fun getPairedDevices(): Set<BluetoothDevice> = BA.bondedDevices

    inner class ConnectedThread// Get the input and output streams, using temp objects because
    // member streams are final
        (socket: BluetoothSocket) : Thread() {
        val mmInStream: InputStream?
        val mmOutStream: OutputStream?

        init {
            var tmpIn: InputStream? = null
            var tmpOut: OutputStream? = null
            try {
                tmpIn = socket.inputStream;
                tmpOut = socket.outputStream;
            } catch (e: IOException) {
            }
            mmInStream = tmpIn
            mmOutStream = tmpOut
        }

        override fun run() {
            val buffer = ByteArray(30)  // buffer store for the stream
            var bytes: Int // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    var numberofbytes = mmInStream?.available()
                    Timber.i("BluetoothHelper: connect thread nubmer of bytes available $numberofbytes")
                    // Read from the InputStream
                    bytes = mmInStream?.read(buffer)!!
                    mmInStream
//                    val input = BitInput(StreamInput(mmInStream))
//                    input.align(1); // 8-bit byte align; padding

//                    input.readBoolean()
                    h.obtainMessage(RECIEVE_MESSAGE, bytes, -1, buffer)
                        ?.sendToTarget()     // Send to message queue Handler
                } catch (e: IOException) {
                    break
                }
            }
        }

        fun write(bufferedMessage: ByteArray) {
            try {
                mmOutStream!!.write(bufferedMessage)
            } catch (e: IOException) {
                Timber.i("error eccured in writing bufferred message .")
            }
        }
    }
}