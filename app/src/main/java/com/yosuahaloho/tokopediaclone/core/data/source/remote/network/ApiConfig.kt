package com.yosuahaloho.tokopediaclone.core.data.source.remote.network

import com.google.gson.GsonBuilder
import com.yosuahaloho.tokopediaclone.BuildConfig
import com.yosuahaloho.tokopediaclone.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiConfig {

    private val BASE_URL = if (BuildConfig.DEBUG) {
        Constants.BASE_URL
    } else {
        Constants.ONLINE_URL
    } + "api/"

    val instance: ApiService by lazy {
//        val gson = GsonBuilder()
//            .setLenient()
//            .create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }

//    private val client: Retrofit
//        get() {
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(60, TimeUnit.SECONDS)
//                .build()
//
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)
//                .build()
//        }
//
//
//    val provideApiService: ApiService
//        get() = client.create(ApiService::class.java)


}