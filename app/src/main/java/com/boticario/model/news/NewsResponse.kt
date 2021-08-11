package com.boticario.model.news

import java.io.Serializable

data class NewsResponse(
    val news: List<News>
) : Serializable