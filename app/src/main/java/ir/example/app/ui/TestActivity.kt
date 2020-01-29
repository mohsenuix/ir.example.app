package ir.example.app.ui

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import ir.example.app.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class TestActivity : AppCompatActivity() {
//    private var deviceInterface: SimpleBluetoothDeviceInterface? = null
//    private val bluetoothManager = BluetoothManagerImpl.instance
    lateinit var recyclerAdapter : BluetoothAdapter

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context?,
            intent: Intent
        ) { // Get extra data included in the Intent
            val message = intent.getStringExtra("message")
            Timber.i("messageReceiver ${message.toByteArray().toList()}")

            val devices = intent.getParcelableArrayListExtra<BluetoothDevice>("devices")
            recyclerAdapter =  BluetoothAdapter(devices) {
                    bluetoothDevice -> connectDevice(bluetoothDevice.address)
            }
            pairedRecycler.layoutManager = LinearLayoutManager(this)
//        recycler.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation).also {
//            //todo deprecated
//            it.setDrawable(resources.getDrawable(R.drawable.white_divider))
//        })
            pairedRecycler.adapter = recyclerAdapter
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver, IntentFilter("BluetoothUpdates")
        )
//        if (bluetoothManager == null) {
//            // Bluetooth unavailable on this device :( tell the user
////            Toast.makeText(context, "Bluetooth not available.", Toast.LENGTH_LONG).show(); // Replace context with your context instance.
//            finish()
//        }
//
//        btnSend.setOnClickListener {
//            deviceInterface!!.sendMessage(byteArrayOf(85,85,1,1,-17,-65,-67,7,114,6,50))
//        }
//        btnSendCommand.setOnClickListener {
//            deviceInterface!!.sendMessage(byteArrayOf(85,85,1,5))
//        }
//
//        val pairedDevices =
//            bluetoothManager!!.pairedDevices


//        for (device in pairedDevices) {
//            Timber.i("My Bluetooth App Device name: ${device.name}")
////            Log.d("My Bluetooth App", "Device MAC Address: " + device.address)
//        }
    }
//    var disposable :Disposable? =null
//    private fun connectDevice(mac: String) {
//        bluetoothManager?.closeDevice(mac)
//        disposable?.dispose()
//        disposable = bluetoothManager?.openSerialDevice(mac)
//            ?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe(this::onConnected, this::onError)
//    }
//
//    private fun onConnected(connectedDevice: BluetoothSerialDevice) { // You are now connected to this device!
//// Here you may want to retain an instance to your device:
//        deviceInterface = connectedDevice.toSimpleDeviceInterface()
//        // Listen to bluetooth events
//        deviceInterface?.setListeners(this::onMessageReceived, this::onMessageSent, this::onError)
//        // Let's send a message:
////        deviceInterface?.sendMessage("Hello world!".toByteArray())
//    }


//    private fun onMessageSent(message: ByteArray) { // We sent a message! Handle it here.
////        Toast.makeText(context, "Sent a message! Message was: $message", Toast.LENGTH_LONG)
////            .show() // Replace context with your context instance.
//    Timber.i("OnMessageSent ${message.toList()}")
//
//    }
//
//    private fun onMessageReceived(message: ByteArray) { // We received a message! Handle it here.
////        Toast.makeText(context, "Received a message! Message was: $message", Toast.LENGTH_LONG)
////            .show() // Replace context with your context instance.
//
//        Timber.i("OnMessageReceived ${message.toList()}")
//
//
//
//    }
//
//    private fun onError(error: Throwable) {
//        Timber.i("OnError $error")
//    }
}
