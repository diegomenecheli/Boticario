package com.boticario.model.posts

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "posts")
data class PostsResponseItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val body: String,
    val email: String?,
    val name: String?,
    var postId: Int = 0
) : Serializable