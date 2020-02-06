package ir.example.app.util.model

import java.io.InputStream
import java.io.OutputStream

open class Command()  {

    class Encoding :IEncoding {

        val commands : HashMap<Byte,Command>  = HashMap()

        init {

        }

        override fun encode(t: Any, outputStream: OutputStream) {

        }

        override fun decode(inputStream: InputStream): Command {
            return Command(1)
        }


    }
}