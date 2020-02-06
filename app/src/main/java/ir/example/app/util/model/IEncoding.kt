package ir.example.app.util.model

import java.io.InputStream
import java.io.OutputStream

interface IEncoding {
    fun encode(any:Any ,outputStream: OutputStream)
    fun decode(inputStream: InputStream):Any
}