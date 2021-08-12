package com.boticario.model.login.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.boticario.model.login.Login

@Dao
interface LoginDao {

    @Query("SELECT * FROM login WHERE username LIKE :userName AND password LIKE :password ")
    suspend fun getByUserNameAndPassword(userName: String, password: String): Login

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLogin(user: Login)

}