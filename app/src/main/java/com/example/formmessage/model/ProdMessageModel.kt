package com.example.formmessage.model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProdMessageModel(private val application: Application): MessageModel {

    private val database = Firebase.database.getReference("message")

    override val messageMap = MutableLiveData<Map<String, MessageRecord>>()

    override fun onDelete(messageId: String) {
        database.child(messageId).removeValue()
    }

    init {
        database.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val recordMap = mutableMapOf<String, MessageRecord>()

                // Read the data from the snapshot
                snapshot.children.forEach {
                    val key = it.key
                    val timestamp = it.child("timestamp").getValue(Long::class.java) ?: 0
                    val email = it.child("email").getValue(String::class.java) ?: ""
                    val subject = it.child("subject").getValue(String::class.java) ?: ""
                    val message = it.child("message").getValue(String::class.java) ?: ""

                    key?.let { messageId ->
                        val record = MessageRecord(messageId, timestamp, email, subject, message)
                        recordMap[messageId] = record
                    }
                }

                // Update the message map.
                messageMap.value = recordMap
            }

            override fun onCancelled(error: DatabaseError) {
                // Show a toast if something went wrong.
                Toast.makeText(application, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}