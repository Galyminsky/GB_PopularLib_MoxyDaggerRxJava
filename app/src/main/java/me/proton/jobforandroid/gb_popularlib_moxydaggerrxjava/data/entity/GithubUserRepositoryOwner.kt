package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity

import com.google.gson.annotations.SerializedName

data class GithubUserRepositoryOwner(
    @SerializedName("login")
    var login: String,

    @SerializedName("id")
    val userId: Long,
)
