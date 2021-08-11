package com.boticario.presenter.news

import com.boticario.model.news.NewsResponse

interface NewsHome {
    interface Presenter {
        fun requestAll()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}