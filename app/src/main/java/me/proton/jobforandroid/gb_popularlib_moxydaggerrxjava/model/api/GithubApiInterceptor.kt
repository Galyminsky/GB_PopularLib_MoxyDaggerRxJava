package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.api


import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.BuildConfig.GITHUB_USER_NAME
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.BuildConfig.GITHUB_USER_PASS
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

object GithubApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .header("Authorization", Credentials.basic(GITHUB_USER_NAME, GITHUB_USER_PASS))
                .build()
        )
}