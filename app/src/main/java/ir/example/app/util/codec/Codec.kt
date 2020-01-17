package ir.example.app.util.codec

interface Codec {
    fun decode(data:ByteArray):String
    fun encode(data:String):ByteArray
}