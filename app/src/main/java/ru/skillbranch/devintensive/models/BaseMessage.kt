package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMessage (
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()

) {

    abstract fun formatMessage() : String
    companion object AbstractFactory{
        var lastId = -1
        fun makeMessage(from: User?,chat: Chat,date: Date = Date(),payload:Any?,type :String = "text",isInComing: Boolean= false) : BaseMessage {
            lastId++
            return when(type) {
                "image"->ImageMessage("$lastId",from,chat,isInComing,date = date,image = payload as String)
                else ->TextMessage("$lastId",from,chat,isInComing,date = date,text = payload as String)
            }
        }
    }
}