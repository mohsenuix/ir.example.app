package ir.example.app.util.model

import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception

class ReadCommand(val t : Byte,
                  val dataId:Byte) : CommandBase() {

    class Encoding : CommandBase.Encoding() {
         override fun encodeCore(t:Command, outputStream: OutputStream){

         }
         override fun decodeCore(inputStream: InputStream):Command{
            throw Exception()
         }

    }

}
