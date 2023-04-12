package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubUser

data class GitHubUserViewModel(
    val login: String,
    val avatar: String,
    val repos_url: String,
) {

    object Mapper {

        fun map(user: RoomGithubUser) =
            GitHubUserViewModel(
                user.login,
                user.avatar,
                user.repos_url
            )
    }
}