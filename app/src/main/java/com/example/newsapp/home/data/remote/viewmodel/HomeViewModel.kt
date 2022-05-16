package com.example.newsapp.home.data.remote.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.home.data.model.NewsResponse
import com.example.newsapp.home.data.remote.repository.HomeRepository
import com.example.newsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private var mutableHeadlines: MutableStateFlow<Resource<NewsResponse>> =
        MutableStateFlow(Resource.Empty())
    val _headlines: StateFlow<Resource<NewsResponse>> = mutableHeadlines


    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()


    fun getHeadlines(countryCode: String, pageNumber: Int) = viewModelScope.launch {
        mutableHeadlines.value = Resource.Loading()
        repository.getTopHeadlines(countryCode, pageNumber).catch { e ->
            mutableHeadlines.value = Resource.Error(e.localizedMessage)
        }.collect { response ->
            mutableHeadlines.value = Resource.Success(response)
        }
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = repository.searchNews(searchQuery)
        searchNews.postValue(handleSearchResponse(response))
    }

    private fun handleSearchResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }


}