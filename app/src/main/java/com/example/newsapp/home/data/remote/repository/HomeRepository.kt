package com.example.newsapp.home.data.remote.repository

import com.example.newsapp.home.data.model.NewsResponse
import com.example.newsapp.home.data.remote.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: Api) {

    fun getTopHeadlines(countryCode: String, pageNumber: Int): Flow<NewsResponse> = flow {
        emit(api.getNewsHeadlines(countryCode, pageNumber))
    }.flowOn(Dispatchers.IO)

    suspend fun searchNews(searchQuery: String) = api.searchForNews(searchQuery)

}