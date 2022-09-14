package ru.shurikus.coreNetwork.di

import com.pluto.plugins.network.PlutoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.shurikus.coreNetwork.BuildConfig
import ru.shurikus.coreNetwork.api.ApiService
import ru.shurikus.coreNetwork.interceptors.ApiQueryInterceptor
import ru.shurikus.coreNetwork.interceptors.MockInterceptor

internal fun provideOkHttpClient(apiKey: String) = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(MockInterceptor())
        .addInterceptor(ApiQueryInterceptor(apiKey))
        .addInterceptor(loggingInterceptor)
        .addInterceptor(PlutoInterceptor())
        .build()
} else {
    OkHttpClient.Builder()
        .addInterceptor(ApiQueryInterceptor(apiKey))
        .build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()

internal fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)