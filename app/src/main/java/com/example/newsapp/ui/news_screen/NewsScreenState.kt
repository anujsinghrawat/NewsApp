package com.example.newsapp.ui.news_screen

import com.example.newsapp.data.remote.Articles

data class NewsScreenState(
    val isLoading : Boolean = false,
    val articles: List<Articles> = emptyList(),
    val error : String? = null,
    val isSearchBarVisible : Boolean = false,
    val selectedArticle : Articles? = null,
    val category: String = "General",
    val searchQuery: String =""

)
