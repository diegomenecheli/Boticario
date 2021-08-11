package com.boticario.model.news

import java.io.Serializable

data class User(
    val name: String,
    val profile_picture: String
) : Serializable