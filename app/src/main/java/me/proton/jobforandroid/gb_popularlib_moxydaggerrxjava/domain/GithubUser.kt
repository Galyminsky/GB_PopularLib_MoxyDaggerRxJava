package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val id: Int,
    val avatarUrl: String
) : Parcelable
