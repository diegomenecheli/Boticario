package com.boticario.model.posts.db

import androidx.room.*
import com.boticario.model.posts.PostsResponseItem

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(post: PostsResponseItem) : Long

    @Query("SELECT * FROM posts")
    fun getAll(): List<PostsResponseItem>

    @Delete
    suspend fun delete(post: PostsResponseItem)

}