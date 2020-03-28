package com.example.formmessage.model

data class MessageRecord(
    val id: String,
    val timestamp: Long,
    val email: String,
    val subject: String,
    val message: String)
