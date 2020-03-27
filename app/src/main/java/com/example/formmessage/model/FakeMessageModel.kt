package com.example.formmessage.model

import androidx.lifecycle.MutableLiveData

class FakeMessageModel: MessageModel {
    override val messageMap = MutableLiveData<Map<String, MessageRecord>>().apply {
        value = mapOf(
            "first" to MessageRecord("first", 1585219729000, "foo@example.com", "Hello", "Hello there!"),
            "second" to MessageRecord("second", 1585216542000, "bar@example.com", "Hey", "Hey there!"),
            "third" to MessageRecord("third", 1585133329000, "baz@example.com", "Hi!", "Hi there!"))
    }

    override fun onDelete(messageId: String) {
        messageMap.value = messageMap.value?.filter { it.value.id != messageId }
    }
}
