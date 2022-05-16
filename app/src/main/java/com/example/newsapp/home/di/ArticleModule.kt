package com.example.newsapp.home.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.home.data.local.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ArticleModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_database"
        )
            .build()

    @Provides
    @Singleton
    fun provideDao(db: ArticleDatabase) = db.getDao()
}