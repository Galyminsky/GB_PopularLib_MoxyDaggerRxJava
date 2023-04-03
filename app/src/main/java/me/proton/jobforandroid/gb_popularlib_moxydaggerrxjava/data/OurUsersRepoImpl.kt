package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data

import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.GithubUsersRepo


class OurUsersRepoImpl : GithubUsersRepo {

    private val data: List<GithubUser> = listOf(
        GithubUser("Alex", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        GithubUser("Max", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        GithubUser("Petr", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
        GithubUser("Ivan", 4, "https://avatars.githubusercontent.com/u/4?v=4"),
        GithubUser("Yuriy", 5, "https://avatars.githubusercontent.com/u/5?v=4"),
        GithubUser("Maksim", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        GithubUser("Bruno", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        GithubUser("Ivey", 8, "https://avatars.githubusercontent.com/u/17?v=4"),
        GithubUser("Sergey", 9, "https://avatars.githubusercontent.com/u/18?v=4"),
        GithubUser("Andrew", 10, "https://avatars.githubusercontent.com/u/19?v=4"),
        GithubUser("Alexandr", 11, "https://avatars.githubusercontent.com/u/20?v=4"),
    )

    override fun getUsers() = Single.just(data)
}