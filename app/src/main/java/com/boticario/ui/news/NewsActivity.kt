package com.boticario.ui.news

import android.view.View
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.boticario.databinding.ActivityNewsBinding
import com.boticario.model.news.NewsResponse
import com.boticario.model.news.data.NewsDataSource
import com.boticario.presenter.ViewHome
import com.boticario.presenter.news.NewsPresenter
import com.boticario.ui.AbstractActivity

class NewsActivity : AbstractActivity(), ViewHome.View {

    private lateinit var presenter: NewsPresenter
    private lateinit var binding: ActivityNewsBinding

    override fun getLayout(): ViewBinding {
        binding = ActivityNewsBinding.inflate(layoutInflater)
        return binding
    }

    override fun onInject() {
        val dataSource = NewsDataSource()
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
    }

    override fun showProgressBar() {
        binding.loading.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        binding.loading.visibility = View.GONE
    }

    override fun showForecast(news: NewsResponse) {
        binding.teste.text = news.news[0].user.name
    }


}