package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.api

import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUserRepository
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    //получить список пользователей
    @GET("users")
    fun getUsers(): Single<List<GithubUser>>

    //получить конкретного пользователя
    @GET("users/{login}")
    fun getUser(@Path("login") login: String): Single<GithubUser>

    //получить список репозиториев пользователя
    @GET("users/{login}/repos")
    fun getRepositories(@Path("login") login: String): Single<List<GithubUserRepository>>

    //получить список репозиториев пользователя
    @GET("repos/{login}/{repository}")
    fun getRepository(
        @Path("login") login: String,
        @Path("repository") repository: String,
    ): Single<GithubUserRepository>
}