package ir.example.app.util

import ir.example.app.util.model.MessageType

class MessageParser {

    fun getLastMessage(buffer: ByteArray): ByteArray {
        for (i in buffer.size - 1 downTo 1)
            if (buffer[i] == 85.toByte() && buffer[i - 1] == 85.toByte())
                return buffer.copyOfRange(i - 1, buffer.size - 1) //todo check if copy is deep
        return ByteArray(0)
    }

    fun getMessageType(message: ByteArray): MessageType =
        if (message[2] == 1.toByte()) MessageType.data else MessageType.command


    fun getFormedMessage(message: ByteArray): Triple<MessageType,Byte, Any>? {
        val messageType = getMessageType(message)
        when {
            messageType == MessageType.command -> {
                val commandId = message[3]
                val commandParam = message[4]
                return Triple(messageType,commandId,commandParam)
            }
            messageType == MessageType.data -> {
                val dataId = message[3]
                val dataParam = message.copyOfRange(4,message.size-2)
                return Triple(messageType,dataId,dataParam)
                //todo check if data is correct ‫‪CRC-8‬‬ 1 byte last data
            }
        }
        return null
    }

    fun parseData(data: ByteArray , messageType: MessageType){

    }
}