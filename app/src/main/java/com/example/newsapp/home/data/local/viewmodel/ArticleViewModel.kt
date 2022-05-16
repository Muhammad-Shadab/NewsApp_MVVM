package com.example.newsapp.home.data.local.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.home.data.local.repository.ArticleRepository
import com.example.newsapp.home.data.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(private val articleRepository: ArticleRepository) :
    ViewModel() {

    fun insertUpdate(article: Article) = viewModelScope.launch {
        articleRepository.insertUpdate(article)
    }

    fun delete(article: Article) = viewModelScope.launch {
        articleRepository.deleteArticle(article)
    }

    fun getArticle() = articleRepository.getArticles()

}