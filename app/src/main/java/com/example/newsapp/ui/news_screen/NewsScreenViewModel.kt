package com.example.newsapp.ui.news_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.domain.repository.NewsRepository
import com.example.newsapp.data.remote.Articles
import com.example.newsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsScreenViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    var articles by mutableStateOf<List<Articles>>(emptyList())
    var state by mutableStateOf(NewsScreenState())

    fun onEvent(event: NewsScreenEvent){
        when(event){
            is NewsScreenEvent.OnCategoryChanged -> {
                state = state.copy(category = event.category)
                getNewsArticles(state.category)
            }
            NewsScreenEvent.OnCloseIconClicked -> {
                state= state.copy(isSearchBarVisible = false)
            }
            is NewsScreenEvent.OnNewsCardClicked -> {
                state = state.copy(selectedArticle = event.article)
            }
            NewsScreenEvent.OnSearchIconClicked -> {
                state = state.copy(isSearchBarVisible = true, articles = emptyList())
                getNewsArticles(category = state.category)
            }
            is NewsScreenEvent.OnSearchQueryChanged -> {
                state = state.copy(searchQuery = event.searchQuery)
                searchForNews(query = state.searchQuery)
            }
        }
    }


    init {
        getNewsArticles(category = "general")
    }

    private fun getNewsArticles(category: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = newsRepository.getTopHeadlines(category = category)
            when (result) {
                is Resource.Success -> {
                    state = state.copy(articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null)

                }

                is Resource.Error -> {
                    state =state.copy(
                        error = result.message,
                        isLoading = false,
                    articles = emptyList()

                    )
                }
            }
        }
    }

    private fun searchForNews(query: String) {
        if(query.isEmpty()) return

        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = newsRepository.seachForNews(query = query)
            when (result) {
                is Resource.Success -> {
                    state = state.copy(articles = result.data ?: emptyList(),
                        isLoading = false,
                        error = null)

                }

                is Resource.Error -> {
                    state =state.copy(
                        error = result.message,
                        isLoading = false,
                        articles = emptyList()

                    )
                }
            }
        }
    }
}