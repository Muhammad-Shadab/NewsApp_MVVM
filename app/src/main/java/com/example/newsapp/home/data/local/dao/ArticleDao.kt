package com.example.newsapp.home.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.home.data.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpdate(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticle(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}