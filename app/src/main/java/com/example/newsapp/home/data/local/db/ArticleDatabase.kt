package com.example.newsapp.home.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.converter.Converter
import com.example.newsapp.home.data.local.dao.ArticleDao
import com.example.newsapp.home.data.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class ArticleDatabase: RoomDatabase() {

    abstract fun getDao(): ArticleDao

}