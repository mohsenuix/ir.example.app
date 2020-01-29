package ir.example.app.util

import ir.example.app.util.model.MessageType

class MessageParser {

    fun getMessageType(message: ByteArray): MessageType =
        if (message[0] == 1.toByte()) MessageType.data else MessageType.command

    fun getFormedMessage(message: ByteArray): Triple<MessageType,Byte, Any>? {
        val messageType = getMessageType(message)
        when {
            messageType == MessageType.command -> {
                val commandId = message[1]
                val commandParam = message[2]
                return Triple(messageType,commandId,commandParam)
            }
            messageType == MessageType.data -> {
                val dataId = message[1]
                val dataParam = message.copyOfRange(2,message.size-2)
                return Triple(messageType,dataId,dataParam)
                //todo check if data is correct ‫‪CRC-8‬‬ 1 byte last data
            }
        }
        return null
    }
}