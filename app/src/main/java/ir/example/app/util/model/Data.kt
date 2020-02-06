package ir.example.app.util.model

import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class Data() : Packet() {

    class Encoding : IEncoding {
        override fun encode(t: Any, outputStream: OutputStream) {
        }

        override fun decode(inputStream: InputStream): Any {
            throw Exception()
        }

    }
}