package ir.example.app.util.serialbluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import com.harrysoft.androidbluetoothserial.BluetoothSerialDevice
import io.reactivex.Single
import java.nio.charset.Charset
import java.util.*

/**
 * Implementation of BluetoothManager, package-private
 */
internal class BluetoothManagerImpl(private val adapter: BluetoothAdapter) : BluetoothManager {
    private val devices: MutableMap<String, BluetoothSerialDeviceImpl> = mutableMapOf()

    override val pairedDevices: Collection<BluetoothDevice>
        get() = adapter.bondedDevices

    override fun openSerialDevice(mac: String): Single<BluetoothSerialDevice> {
        return openSerialDevice(mac, Charset.forName("UTF-8"))
//        return openSerialDevice(mac, StandardCharsets.UTF_8)
    }

    override fun openSerialDevice(mac: String, charset: Charset): Single<BluetoothSerialDevice> {
        return if (devices.containsKey(mac)) {
            Single.just(devices[mac]!!)
        } else {
            Single.fromCallable {
                try {
                    val device = adapter.getRemoteDevice(mac)
                    val socket = device.createInsecureRfcommSocketToServiceRecord(SPP_UUID)
                    adapter.cancelDiscovery()
                    socket.connect()
                    val serialDevice = BluetoothSerialDeviceImpl(mac, socket, charset)
                    devices[mac] = serialDevice
                    return@fromCallable serialDevice
                } catch (e: Exception) {
                    throw BluetoothConnectException(e)
                }
            }
        }
    }

    override fun closeDevice(mac: String) {
        devices.remove(mac)?.close()
    }

    override fun closeDevice(device: BluetoothSerialDevice) {
        closeDevice(device.mac)
    }

    override fun closeDevice(deviceInterface: SimpleBluetoothDeviceInterface) {
        closeDevice(deviceInterface.device.mac)
    }

    fun close() {
        for (device in devices.values) {
            try {
                device.close()
            } catch (ignored: Throwable) {
            }
        }
        devices.clear()
    }

    companion object {
        val SPP_UUID: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        /**
         * @return A BluetoothManager instance if the device
         * has Bluetooth or null otherwise.
         */
        @JvmStatic
        val instance: BluetoothManager? by lazy {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            if (bluetoothAdapter != null) {
                BluetoothManagerImpl(bluetoothAdapter)
            } else null
        }
    }



}
