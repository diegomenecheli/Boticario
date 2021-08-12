package com.boticario.presenter.signup

import com.boticario.model.login.Login

interface SignUpHome {
    interface Presenter {
        fun registerUser(user: Login)
        fun onSuccess(login: Login)
        fun onError(message: String)
        fun onComplete()
    }
}