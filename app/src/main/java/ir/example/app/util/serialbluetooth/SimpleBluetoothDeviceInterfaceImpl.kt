package ir.example.app.util.serialbluetooth

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.reflect.KFunction1

/**
 * Implementation of SimpleBluetoothDeviceInterface, package-private
 */
internal class SimpleBluetoothDeviceInterfaceImpl(override val device: BluetoothSerialDeviceImpl) :
    SimpleBluetoothDeviceInterface {
    private val compositeDisposable = CompositeDisposable()

    private  var messageReceivedListener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>? =null
    private  var messageSentListener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>? = null
    private  var errorListener: KFunction1<@ParameterName(name = "error") Throwable, Unit>? = null

    init {
        compositeDisposable.add(device.openMessageStream()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ messageReceivedListener?.invoke(it) }, { errorListener?.invoke(it) }))
    }

    override fun sendMessage(message: ByteArray) {
        device.checkNotClosed()
        compositeDisposable.add(device.send(message)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ messageSentListener?.invoke(message) }, { errorListener?.invoke(it) }))
    }

    override fun setListeners(
        messageReceivedListener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>?,
        messageSentListener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>?,
        errorListener: KFunction1<@ParameterName(name = "error") Throwable, Unit>?
    ) {
        this.messageReceivedListener = messageReceivedListener
        this.messageSentListener = messageSentListener
        this.errorListener = errorListener
    }

    override fun setMessageReceivedListener(listener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>?) {
        messageReceivedListener = listener
    }

    override fun setMessageSentListener(listener: KFunction1<@ParameterName(name = "message") ByteArray, Unit>?) {
        messageSentListener = listener
    }

    override fun setErrorListener(listener: KFunction1<@ParameterName(name = "error") Throwable, Unit>?) {
        errorListener = listener
    }

    fun close() {
        compositeDisposable.dispose()
    }
}


//:30:56.106 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 3, 77, 7, -17, -65, -67, 5, -17, -65, -67]
//01-23 12:30:56.113 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 8, 19, 6, 0, 0, 25]
//01-23 12:30:56.120 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 1, -17, -65, -67, 6, 112, 6, 44]
//01-23 12:30:56.121 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 3, 78, 7, -17, -65, -67, 5, -17, -65, -67]
//01-23 12:30:56.122 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 8, 20, 6, 0, 0, 26]
//01-23 12:30:56.122 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 1, -17, -65, -67, 6, 109, 6, 36]
//01-23 12:30:56.123 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 3, 78, 7, -17, -65, -67, 5, -17, -65, -67]
//01-23 12:30:56.124 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 8, 18, 6, 0, 0, 24]
//01-23 12:30:56.125 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 1, -17, -65, -67, 6, 105, 6, 27]
//01-23 12:30:56.129 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 3, 78, 7, -17, -65, -67, 5, -17, -65, -67]
//01-23 12:30:56.130 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 8, 16, 6, 0, 0, 22]
//01-23 12:30:56.131 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 1, -17, -65, -67, 6, 102, 6, 21]
//01-23 12:30:56.132 5849-5849/de.joyn.myapplication I/TestActivity: OnMessageReceived [1, 3, 78, 7, -17, -65, -67, 5, -17, -65, -67]