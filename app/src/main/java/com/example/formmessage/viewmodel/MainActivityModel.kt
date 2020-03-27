package com.example.formmessage.viewmodel

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.formmessage.model.MessageModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityModel: ViewModel(), KoinComponent {

    private val messageModel by inject<MessageModel>()

    val messageList = Transformations.map(messageModel.messageMap) { messageMap ->
        messageMap.values.sortedBy { it.timestamp }
    }
}
