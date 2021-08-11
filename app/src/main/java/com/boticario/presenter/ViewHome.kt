package com.boticario.presenter

import com.boticario.model.news.NewsResponse

interface ViewHome {
    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showForecast(news: NewsResponse)
    }
}