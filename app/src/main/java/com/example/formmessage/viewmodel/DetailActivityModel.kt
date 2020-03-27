package com.example.formmessage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.formmessage.model.MessageModel
import org.koin.core.KoinComponent
import org.koin.core.inject

@Suppress("UNCHECKED_CAST")
class DetailActivityModelFactory(private val messageId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailActivityModel(messageId) as T
    }
}

class DetailActivityModel(private val messageId: String): ViewModel(), KoinComponent {
    private val messageModel by inject<MessageModel>()

    val message = messageModel.messageMap.value?.get(messageId)

    fun onDeleteMessage() = messageModel.onDelete(messageId)
}
