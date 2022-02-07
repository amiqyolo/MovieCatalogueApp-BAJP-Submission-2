package com.android.moviecatalogueapp.data.source.remote.retrofit

import com.android.moviecatalogueapp.BuildConfig
import com.android.moviecatalogueapp.utils.Constants.API_KEY
import com.android.moviecatalogueapp.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {

    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

            val client = OkHttpClient.Builder().apply {
                addInterceptor { chain ->
                    val url = chain.request().url
                        .newBuilder()
                        .addQueryParameter("api_key", API_KEY)
                        .build()

                    val request: Request =
                        chain.request().newBuilder().apply {
                            url(url)
                            addHeader("Accept", "application/json")
                        }.build()
                    chain.proceed(request)
                }
                addInterceptor(loggingInterceptor)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                client(client)
            }.build()

            return retrofit.create(ApiService::class.java)
        }
    }

}