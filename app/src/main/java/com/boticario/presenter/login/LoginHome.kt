package com.boticario.presenter.login

import com.boticario.model.login.Login

interface LoginHome {
    interface Presenter {
        fun validateUser(email: String, password: String)
        fun onSuccess(login: Login)
        fun onError(message: String)
        fun onComplete()
    }
}