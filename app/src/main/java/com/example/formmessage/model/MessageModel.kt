package com.example.formmessage.model

import androidx.lifecycle.LiveData

interface MessageModel {
    val messageMap: LiveData<Map<String, MessageRecord>>
    fun onDelete(messageId: String)
}
