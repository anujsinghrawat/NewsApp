package com.example.newsapp.ui.news_screen

import com.example.newsapp.data.remote.Articles

sealed class NewsScreenEvent{
    data class OnNewsCardClicked(val article : Articles) : NewsScreenEvent()
    data class OnCategoryChanged(val category: String) :NewsScreenEvent()
    data class OnSearchQueryChanged(val searchQuery :String) : NewsScreenEvent()
    object OnSearchIconClicked:NewsScreenEvent()
    object OnCloseIconClicked:NewsScreenEvent()
}
