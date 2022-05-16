package com.example.newsapp.home.data.local.repository

import com.example.newsapp.home.data.local.dao.ArticleDao
import com.example.newsapp.home.data.model.Article
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val dao: ArticleDao
) {

    suspend fun insertUpdate(article: Article) = dao.insertUpdate(article)

    fun getArticles() = dao.getAllArticle()

    suspend fun deleteArticle(article: Article) = dao.deleteArticle(article)

}