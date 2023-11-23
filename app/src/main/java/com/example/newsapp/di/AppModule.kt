package com.example.newsapp.di

import com.example.newsapp.data.domain.repository.NewsRepository
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.remote.NewsApi.Companion.BASE_URL
import com.example.newsapp.data.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent :: class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(NewsApi :: class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi :NewsApi) : NewsRepository{
        return NewsRepositoryImpl(newsApi)
    }

}