package com.boticario.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.boticario.databinding.MyPostItemBinding
import com.boticario.model.posts.PostsResponseItem

class MyPostAdapter : RecyclerView.Adapter<MyPostAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val itemBinding: MyPostItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(post: PostsResponseItem) {
            itemBinding.namePost.text = post.name
            itemBinding.emailPost.text = post.email
            itemBinding.post.text = post.body

        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<PostsResponseItem>() {
        override fun areItemsTheSame(
            oldItem: PostsResponseItem,
            newItem: PostsResponseItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PostsResponseItem,
            newItem: PostsResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.itemView.apply {
            holder.bind(post)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemBinding =
            MyPostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(itemBinding)
    }


    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((PostsResponseItem) -> Unit)? = null

    fun setOnClickListener(listener: (PostsResponseItem) -> Unit) {
        onItemClickListener = listener
    }
}