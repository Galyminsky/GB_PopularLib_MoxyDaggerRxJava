package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubRepository

data class GitHubUserRepoViewModel(
    val name: String,
    val description: String,
    val language: String,
    val forks_count: Int,
) {

    object Mapper {

        fun map(user: RoomGithubRepository) =
            GitHubUserRepoViewModel(
                user.name,
                user.description!!,
                user.language,
                user.forks_count
            )
    }
}