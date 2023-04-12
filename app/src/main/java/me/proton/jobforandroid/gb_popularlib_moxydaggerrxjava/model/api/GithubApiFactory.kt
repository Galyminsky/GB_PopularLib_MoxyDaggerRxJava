package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GithubApiFactory {

    private val gson: Gson = GsonBuilder().create()

    fun create(): GithubApi =
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(GithubApiInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GithubApi::class.java)
}