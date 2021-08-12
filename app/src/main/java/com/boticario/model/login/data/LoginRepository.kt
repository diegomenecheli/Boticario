package com.boticario.model.login.data

import com.boticario.model.login.Login
import com.boticario.model.login.db.LoginDataBase

class LoginRepository(private val db: LoginDataBase) {

    suspend fun getByUserNameAndPassword(username: String, password: String): Login =
        db.getLoginDao().getByUserNameAndPassword(username, password)

    suspend fun insertLogin(user: Login) = db.getLoginDao().insertLogin(user)

}