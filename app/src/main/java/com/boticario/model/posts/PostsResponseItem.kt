package com.boticario.model.posts

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "posts")
data class PostsResponseItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val body: String,
    val email: String,
    val name: String,
    val postId: Int
) : Serializable