package com.boticario.presenter


import com.boticario.model.login.Login
import com.boticario.model.news.NewsResponse
import com.boticario.model.posts.PostsResponseItem

interface ViewHome {
    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showNews(news: NewsResponse)
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

    interface PostView {
        fun showFailure(message: String)
        fun registerPost(post: PostsResponseItem)
        fun getAll(post: List<PostsResponseItem>)
        fun hideProgressBar()
        fun showProgressBar()

    }
}