package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login: String
) : Parcelable