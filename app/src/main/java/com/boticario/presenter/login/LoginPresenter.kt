package com.boticario.presenter.login

import com.boticario.model.login.Login
import com.boticario.model.login.data.LoginDataSource
import com.boticario.presenter.ViewHome

class LoginPresenter(
    private val view: ViewHome.LoginView,
    private val dataSource: LoginDataSource
) : LoginHome.Presenter {

    override fun validateUser(email: String, password: String) {
        this.view.showProgressBar()
        this.dataSource.getByUserNameAndPassword(this, email, password)
    }

    override fun onSuccess(login: Login) {
        this.view.saveLogin(login)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}