package com.boticario.model.news

import java.io.Serializable

data class Message(
    val content: String,
    val created_at: String
) : Serializable