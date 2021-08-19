package com.boticario.presenter.post

import com.boticario.model.posts.PostsResponseItem

interface PostHome {

    interface Presenter{
        fun onSuccessShow(articles: List<PostsResponseItem>)
        fun onSave(articles: PostsResponseItem)
        fun onError(message: String)
        fun onComplete()
    }
}