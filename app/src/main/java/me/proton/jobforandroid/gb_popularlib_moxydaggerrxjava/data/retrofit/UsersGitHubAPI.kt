package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.retrofit

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UserEntity
import retrofit2.Call
import retrofit2.http.GET

interface UsersGitHubAPI {
    @GET("users")
    fun getListUsers(): Call<List<UserEntity>>
}