package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUser

data class GithubUserViewModel(
    val login: String,
    val avatar_url: String,
    val repos_url: String,
) {
    object Mapper {
        fun map(user: GithubUser) = GithubUserViewModel(
            user.login.uppercase(), user.avatar, user.repos_url
        )
    }
}