package ir.example.app.util.codec

import ir.example.app.util.model.Command
import ir.example.app.util.model.Data
import ir.example.app.util.model.IEncoding
import ir.example.app.util.model.Packet
import java.io.InputStream
import java.io.Writer
import kotlin.collections.HashMap

class DevicePacket {
    var id : Byte? = null
    val HEADER = listOf<Byte>(0x55,0x55)
    lateinit var packet:Packet

    inner class Encoding{
        val  encodings :HashMap<Byte, IEncoding> = HashMap()
        init {
            encodings[1] = Command.Encoding()
            encodings[2] = Data.Encoding()
        }
        fun encode(writer: Writer, devicePacket: DevicePacket){

        }
        fun decode(input: InputStream) : DevicePacket{
//            if (reader == null)
//                throw ArgumentNullException(nameof(reader));
            var found = 0;
            while (found < HEADER.size)
            {
                val header =  ByteArray(1)
                input.read(header)
                if (header[0] == HEADER[found])
                    found++
                else if (header[0] == HEADER[0])
                    found = 1
                else
                    found = 0
            }

            return DevicePacket().also {
                val tid = ByteArray(1)
                input.read(tid)
                packet = encodings[tid[0]]?.decode(input) as Packet
                id = tid[0]
            }
        }
    }

}