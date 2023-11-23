package com.example.newsapp.data.domain.repository

import com.example.newsapp.data.remote.Articles
import com.example.newsapp.util.Resource
import retrofit2.http.Query

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String
    ) : Resource<List<Articles>>

    suspend fun seachForNews(
        query: String
    ) : Resource<List<Articles>>

}