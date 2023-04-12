package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @SerializedName("id") val id: String,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar: String,
    @SerializedName("repos_url") val repos_url: String,
) : Parcelable
