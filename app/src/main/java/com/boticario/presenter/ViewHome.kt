package com.boticario.presenter


import com.boticario.model.login.Login
import com.boticario.model.news.NewsResponse

interface ViewHome {
    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showForecast(news: NewsResponse)
    }

    interface LoginView {
        fun showFailure(message: String)
        fun saveLogin(login: Login)
        fun hideProgressBar()
        fun showProgressBar()

    }

    interface SignUpView {
        fun showFailure(message: String)
        fun register(user: Login)
        fun hideProgressBar()
        fun showProgressBar()

    }
}