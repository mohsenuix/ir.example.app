package ir.example.app.ui.service

import android.app.Service
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import ir.example.app.util.serialbluetooth.BluetoothManagerImpl
import com.harrysoft.androidbluetoothserial.BluetoothSerialDevice
import ir.example.app.util.serialbluetooth.SimpleBluetoothDeviceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


class BluetoothService : Service() {

    var disposable: Disposable? = null
    private val bluetoothManager = BluetoothManagerImpl.instance
    private var deviceInterface: SimpleBluetoothDeviceInterface? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        if (bluetoothManager == null) {
            // Bluetooth unavailable on this device :( tell the user
//            Toast.makeText(context, "Bluetooth not available.", Toast.LENGTH_LONG).show(); // Replace context with your context instance.
//            finish()
        }

        val pairedDevices =
            bluetoothManager!!.pairedDevices
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun connectDevice(mac: String) {
        bluetoothManager?.closeDevice(mac)
        disposable?.dispose()
        disposable = bluetoothManager?.openSerialDevice(mac)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(this::onConnected, this::onError)
    }

    private fun onConnected(connectedDevice: BluetoothSerialDevice) { // You are now connected to this device!
// Here you may want to retain an instance to your device:
        deviceInterface = connectedDevice.toSimpleDeviceInterface()
        // Listen to bluetooth events
        deviceInterface?.setListeners(this::onMessageReceived, this::onMessageSent, this::onError)
        // Let's send a message:
//        deviceInterface?.sendMessage("Hello world!".toByteArray())
    }


    private fun onMessageSent(message: ByteArray) { // We sent a message! Handle it here.
//        Toast.makeText(context, "Sent a message! Message was: $message", Toast.LENGTH_LONG)
//            .show() // Replace context with your context instance.
        Timber.i("OnMessageSent ${message.toList()}")

    }

    private fun onMessageReceived(message: ByteArray) { // We received a message! Handle it here.
//        Toast.makeText(context, "Received a message! Message was: $message", Toast.LENGTH_LONG)
//            .show() // Replace context with your context instance.
        Timber.i("OnMessageReceived ${message.toList()}")
        sendMessageToActivity(message.toString(Charsets.UTF_8))
    }

    private fun onError(error: Throwable) {
        Timber.i("OnError $error")
    }

    private fun sendMessageToActivity(message: String) {
        val intent = Intent("BluetoothUpdates")
        // You can also include some extra data.
        intent.putExtra("message", message)
//        val b = Bundle()
//        b.putParcelable("Location", l)
//        intent.putExtra("Location", b)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    private fun sendPairedDevicesToActivity(devices: Collection<BluetoothDevice>) {
        val intent = Intent("BluetoothUpdates")
        // You can also include some extra data.
//        intent.parcab("message", devices)
        val b = Bundle()
        b.putParcelableArray("devices", devices.toTypedArray())
        intent.putExtra("s", b)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

}
