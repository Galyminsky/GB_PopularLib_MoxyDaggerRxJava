package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.api

import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUserRepo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GithubApi {

    @GET("/users")
    fun getUsers(@Query("since") since: Int? = null): Single<List<GithubUser>>

    @GET("/users/{username}")
    fun getUserByLogin(@Path("username") login: String): Single<GithubUser>

    @GET
    fun getUserRepos(@Url url: String): Single<List<GithubUserRepo>>
}