package com.boticario.model.posts.data

import com.boticario.model.posts.PostsResponseItem
import com.boticario.model.posts.db.PostDataBase

class PostRepository(private val db: PostDataBase) {

    suspend fun updateInsert(post: PostsResponseItem) = db.getPostDao().updateInsert(post)

    fun getAll(): List<PostsResponseItem> = db.getPostDao().getAll()

    suspend fun delete(post: PostsResponseItem) = db.getPostDao().delete(post)
}