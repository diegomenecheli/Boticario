package com.boticario.presenter.signup

import com.boticario.model.login.Login
import com.boticario.model.login.data.LoginDataSource
import com.boticario.presenter.ViewHome

class SignUpPresenter (
    private val view: ViewHome.SignUpView,
    private val dataSource: LoginDataSource
): SignUpHome.Presenter {

    override fun registerUser(user: Login) {
        this.view.showProgressBar()
        this.dataSource.registerUsers(user, this)
    }

    override fun onSuccess(login: Login) {
        this.view.register(login)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}