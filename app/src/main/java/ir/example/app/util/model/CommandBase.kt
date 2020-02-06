package ir.example.app.util.model

import java.io.InputStream
import java.io.OutputStream

abstract class CommandBase : Packet(){
    abstract class Encoding :IEncoding {

        val commands : HashMap<Byte,Command>  = HashMap()

        init {

        }

        override fun encode(t: Any, outputStream: OutputStream) {
            encodeCore(t as Command,outputStream)
        }

        override fun decode(inputStream: InputStream): Command {
           return decodeCore(inputStream)
        }

        abstract fun encodeCore(t:Command, outputStream: OutputStream)
        abstract fun decodeCore(inputStream: InputStream):Command

    }
}