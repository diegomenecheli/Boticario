package com.boticario.model.posts.data

import android.content.Context
import com.boticario.model.posts.PostsResponseItem
import com.boticario.model.posts.db.PostDataBase
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

    fun deletePost(post: PostsResponseItem?){
        GlobalScope.launch(Dispatchers.Main) {
            post?.let { postDelete ->
                postRepository.delete(postDelete)
            }
        }
    }

    fun getAllPosts(callback: PostHome.Presenter){
        var allPosts: List<PostsResponseItem>
        CoroutineScope(Dispatchers.IO).launch {
            allPosts = postRepository.getAll()

            withContext(Dispatchers.Main){
                callback.onSuccessShow(allPosts)
            }
        }
    }

}