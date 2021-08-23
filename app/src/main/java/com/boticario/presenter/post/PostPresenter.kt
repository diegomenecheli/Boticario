package com.boticario.presenter.post

import com.boticario.model.posts.PostsResponseItem
import com.boticario.model.posts.data.PostDataSource
import com.boticario.presenter.ViewHome

class PostPresenter(
    val view: ViewHome.PostView,
    private val dataSource: PostDataSource
) : PostHome.Presenter {

    override fun onSuccessShow(post: List<PostsResponseItem>) {
        this.view.getAll(post)
    }

    override fun onSave(post: PostsResponseItem) {
        this.view.registerPost(post)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

    fun getAll() {
        this.view.showProgressBar()
        this.dataSource.getAllPosts(this)
    }


    fun savePost(post: PostsResponseItem) {
        this.dataSource.makePost(post, this)
    }

}