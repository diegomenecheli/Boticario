package com.boticario.model.news

import java.io.Serializable

data class News(
    val message: Message,
    val user: User
) : Serializable