package com.boticario.model.news.data

import com.boticario.network.RetrofitInstance
import com.boticario.presenter.news.NewsHome
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewsDataSource {

    fun getAllNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.newsClient.getNews()
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                    callback.onComplete()
                }
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }
}