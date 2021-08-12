package com.boticario.model.login.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boticario.model.login.Login

@Database(entities = [Login::class], version = 1, exportSchema = false)
abstract class LoginDataBase : RoomDatabase() {

    abstract fun getLoginDao(): LoginDao

    companion object {

        @Volatile
        private var instance: LoginDataBase? = null
        private val Lock = Any()

        fun getInstance(context: Context): LoginDataBase =
            instance ?: synchronized(Lock) {
                instance ?: createdDatabase(context).also { instance = it }
            }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                LoginDataBase::class.java,
                "login_db.db"
            ).build()
    }
}