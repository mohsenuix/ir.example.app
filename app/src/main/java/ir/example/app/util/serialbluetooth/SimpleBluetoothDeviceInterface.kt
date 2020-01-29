package ir.example.app.util.serialbluetooth

import com.harrysoft.androidbluetoothserial.BluetoothSerialDevice
import kotlin.reflect.KFunction1

interface SimpleBluetoothDeviceInterface {
    /**
     * @return The BluetoothSerialDevice instance that the interface is wrapping.
     */
    val device: BluetoothSerialDevice

    fun sendMessage(message: ByteArray)

    /**
     * Set all of the listeners for the interfact
     *
     * @param messageReceivedListener Receive message callback
     * @param messageSentListener Send message callback (indicates that a message was successfully sent)
     * @param errorListener Error callback
     */
    fun setListeners(
        messageReceivedListener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>?,
        messageSentListener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>?,
        errorListener: KFunction1<@ParameterName(name = "error") Throwable, Unit>?
    )

    /**
     * Set the message received listener
     *
     * @param listener Receive message callback
     */
    fun setMessageReceivedListener(listener:  KFunction1<@ParameterName(name = "message") ByteArray, Unit>?)

    /**
     * Set the message sent listener
     *
     * @param listener Send message callback (indicates that a message was successfully sent)
     */
    fun setMessageSentListener(listener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>?)

    /**
     * Set the error listener
     *
     * @param listener Error callback
     */
    fun setErrorListener(listener: KFunction1<@ParameterName(name = "error") Throwable, Unit>?)

    interface OnMessageReceivedListener {
        fun onMessageReceived(message: ByteArray)
    }

    interface OnMessageSentListener {
        fun onMessageSent(message: ByteArray)
    }

    interface OnErrorListener {
        fun onError(error: Throwable)
    }
}
