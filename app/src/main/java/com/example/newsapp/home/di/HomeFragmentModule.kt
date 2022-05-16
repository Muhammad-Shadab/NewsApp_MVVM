package com.example.newsapp.home.di

import com.example.newsapp.home.data.remote.Api
import com.example.newsapp.home.data.remote.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeFragmentModule {

    @Provides
    @Singleton
    fun provideRepository(api: Api) = HomeRepository(api)

}