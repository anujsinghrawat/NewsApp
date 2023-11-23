package com.example.newsapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


//    "https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY"

    @GET("top-headlines")
    suspend fun getBreakingNews(
        @Query("category") category: String,
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = API_KEY,
    ) : NewsResponse

    @GET("everything")
    suspend fun searchForNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY,
    ) : NewsResponse


    companion object{
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "bdb1ac28bb9b4c2e9920f331f013885f"
    }
}