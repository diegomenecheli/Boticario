package com.boticario.model.login.data

import android.content.Context
import android.util.Log
import com.boticario.model.login.Login
import com.boticario.model.login.db.LoginDataBase
import com.boticario.presenter.login.LoginHome
import com.boticario.presenter.signup.SignUpHome
import kotlinx.coroutines.*

class LoginDataSource(context: Context) {

    private var db: LoginDataBase = LoginDataBase.getInstance(context)
    private var loginRepository: LoginRepository = LoginRepository(db)

    fun getByUserNameAndPassword(callback: LoginHome.Presenter, email: String, password: String) {
        var login: Login
        CoroutineScope(Dispatchers.IO).launch {
            login = loginRepository.getByUserNameAndPassword(email, password)
            Log.d("xuxa", "getByUserNameAndPassword: $login")
            withContext(Dispatchers.Main) {
                if (login != null) {
                    callback.onSuccess(login)
                    callback.onComplete()
                } else {
                    callback.onError("E-mail ou senha incorreto")
                    callback.onComplete()
                }
            }

        }
    }

    fun registerUsers(login: Login, callback: SignUpHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            loginRepository.insertLogin(login)
            try {
                withContext(Dispatchers.Main) {
                    callback.onSuccess(login)
                    callback.onComplete()
                }
            } catch (e: Throwable) {
                callback.onError("E-mail ou senha incorreto")
                callback.onComplete()
            }
        }
    }
}