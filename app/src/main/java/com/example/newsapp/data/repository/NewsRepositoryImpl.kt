package com.example.newsapp.data.repository

import com.example.newsapp.data.domain.repository.NewsRepository
import com.example.newsapp.data.remote.Articles
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.util.Resource

class NewsRepositoryImpl (
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(category: String): Resource<List<Articles>> {
        return try{
            val response = newsApi.getBreakingNews(category = category)
            Resource.Success(response.articles)
        }catch (e:Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun seachForNews(query: String): Resource<List<Articles>> {
        return try{
            val response = newsApi.searchForNews(query = query)
            Resource.Success(response.articles)
        }catch (e:Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}