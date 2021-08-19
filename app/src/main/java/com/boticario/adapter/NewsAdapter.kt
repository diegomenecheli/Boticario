package com.boticario.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.boticario.databinding.ItemNewsBinding
import com.boticario.model.news.News
import okhttp3.HttpUrl.Companion.toHttpUrl

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val itemBinding: ItemNewsBinding) : RecyclerView.ViewHolder(itemBinding.root){
        fun bind(news: News) {
            itemBinding.newsContent.text = news.message.content
            itemBinding.createdAt.text = news.message.created_at
            itemBinding.createdAt.text = news.user.profile_picture
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(
            oldItem: News,
            newItem: News
        ): Boolean {
            return oldItem.message.created_at == newItem.message.created_at
        }

        override fun areContentsTheSame(
            oldItem: News,
            newItem: News
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = differ.currentList[position]
        holder.itemView.apply {
            holder.bind(news)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }


    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((News) -> Unit)? = null

    fun setOnClickListener(listener: (News) -> Unit) {
        onItemClickListener = listener
    }

}