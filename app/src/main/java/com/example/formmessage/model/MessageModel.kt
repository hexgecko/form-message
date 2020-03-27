package com.example.formmessage.model

import androidx.lifecycle.LiveData

data class MessageRecord(
    val id: String,
    val timestamp: Long,
    val email: String,
    val subject: String,
    val message: String)

interface MessageModel {
    val messageMap: LiveData<Map<String, MessageRecord>>
    fun onDelete(messageId: String)
}
