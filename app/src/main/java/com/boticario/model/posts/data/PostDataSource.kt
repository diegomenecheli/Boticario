package com.boticario.model.posts.data

import android.content.Context
import com.boticario.model.posts.PostsResponseItem
import com.boticario.model.posts.db.PostDataBase
import com.boticario.network.RetrofitInstance
import com.boticario.presenter.post.PostHome
import kotlinx.coroutines.*

class PostDataSource (context: Context) {

    private var db: PostDataBase = PostDataBase(context)
    private var postRepository: PostRepository = PostRepository(db)


    fun makePost(post: PostsResponseItem, callback: PostHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            postRepository.updateInsert(post)
            try {
                withContext(Dispatchers.Main) {
                    callback.onSave(post)
                    callback.onComplete()
                }
            } catch (e: Throwable) {
                callback.onError("Erro em salvar o post")
                callback.onComplete()
            }
        }
    }


    fun getAllPosts(callback: PostHome.Presenter){
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.commentClient.getPosts()
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccessShow(newsResponse)
                    callback.onComplete()
                }
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }


}