package com.tuan.borutoheroes.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tuan.borutoheroes.data.local.BorutoDatabase
import com.tuan.borutoheroes.data.remote.BorutoApi
import com.tuan.borutoheroes.data.repository.RemoteDataSourceImpl
import com.tuan.borutoheroes.domain.repository.RemoteDataSource
import com.tuan.borutoheroes.util.Constants.LOCAL_HOST_BASE_URL
import com.tuan.borutoheroes.util.Constants.CLIENT_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CLIENT_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        val contentType: MediaType = MediaType.get("application/json")

        return Retrofit.Builder()
            .baseUrl(LOCAL_HOST_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideBorutoApi(retrofit: Retrofit): BorutoApi {
        return retrofit.create(BorutoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        borutoApi: BorutoApi,
        borutoDatabase: BorutoDatabase
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            borutoApi = borutoApi,
            borutoDatabase = borutoDatabase
        )
    }
}