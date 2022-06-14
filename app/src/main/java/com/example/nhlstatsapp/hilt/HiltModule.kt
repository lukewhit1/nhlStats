package com.example.nhlstatsapp.hilt

import com.example.nhlstatsapp.api.PlayerStatsRepository
import com.example.nhlstatsapp.api.PlayerStatsRepositoryImpl
import com.example.nhlstatsapp.api.SearchService
import com.example.nhlstatsapp.api.SinglePlayerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class HiltModule {

    @Singleton
    @Provides
    fun provideSearchService(): SearchService {
        return Retrofit.Builder()
            .baseUrl(SearchService.SEARCH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideSinglePlayerService(): SinglePlayerService =
        Retrofit.Builder()
            .baseUrl(SinglePlayerService.SINGLE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SinglePlayerService::class.java)

    @Singleton
    @Provides
    fun provideRepository(): PlayerStatsRepository = PlayerStatsRepositoryImpl(provideSearchService(), provideSinglePlayerService())

    @Singleton
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}