package com.boticario.presenter.news

import com.boticario.model.news.NewsResponse
import com.boticario.model.news.data.NewsDataSource
import com.boticario.presenter.ViewHome

class NewsPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
) : NewsHome.Presenter {


    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showNews(newsResponse)
    }

    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getAllNews(this)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}
