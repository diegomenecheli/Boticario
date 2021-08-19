package com.boticario.network

import com.boticario.model.news.NewsResponse
import com.boticario.model.posts.PostsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    //Get news from api
    @GET("data.json")
    suspend fun getNews(): Response<NewsResponse>

    //Get comments from OTHER api
    @GET("comments")
    suspend fun getPosts(): Response<PostsResponse>
}