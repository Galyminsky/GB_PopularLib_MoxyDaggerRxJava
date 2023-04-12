package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model

import io.reactivex.rxjava3.core.Single

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("User 1"),
        GithubUser("User 2"),
        GithubUser("User 3"),
        GithubUser("User 4"),
        GithubUser("User 5")
    )

    fun getUsers(): Single<List<GithubUser>> {
        return Single.just(repositories)
    }
}