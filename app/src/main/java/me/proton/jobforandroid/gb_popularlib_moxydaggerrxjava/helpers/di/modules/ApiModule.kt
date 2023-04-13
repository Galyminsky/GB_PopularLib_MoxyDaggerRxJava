package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules

import dagger.Module
import dagger.Provides
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.api.GithubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    companion object {
        private const val API_URL = "api_url"
    }

    @Named(API_URL)
    @Provides
    fun provideBaseUrl(): String = "https://api.github.com"

    @Singleton
    @Provides
    fun provideGithubApi(@Named(API_URL) baseUrl: String): GithubApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApi::class.java)

}