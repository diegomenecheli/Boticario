package com.boticario.model.posts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boticario.model.posts.PostsResponseItem

@Database(entities = [PostsResponseItem::class], version = 1, exportSchema = false)
abstract class PostDataBase : RoomDatabase() {

    abstract fun getPostDao(): PostDao

    companion object {

        @Volatile
        private var instance: PostDataBase? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createdDatabase(context).also { postDatabase ->
                instance = postDatabase
            }
        }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostDataBase::class.java,
                "post_db.db"
            ).build()
    }


}