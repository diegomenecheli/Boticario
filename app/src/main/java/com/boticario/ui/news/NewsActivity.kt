package com.boticario.ui.news

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.boticario.adapter.NewsAdapter
import com.boticario.databinding.ActivityNewsBinding
import com.boticario.model.news.NewsResponse
import com.boticario.model.news.data.NewsDataSource
import com.boticario.presenter.ViewHome
import com.boticario.presenter.news.NewsPresenter
import com.boticario.ui.AbstractActivity

class NewsActivity : AbstractActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        NewsAdapter()
    }

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
        configRecycler()
    }

    private fun configRecycler() {
        with(binding.recycler) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
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

    override fun showNews(news: NewsResponse) {
        mainAdapter.differ.submitList(news.news)
    }


}