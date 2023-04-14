package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories

import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUserRepository

interface GithubUsersRepository {
    fun getUsers(): Single<List<GithubUser>>

    fun getUser(userLogin: String): Single<GithubUser>

    fun getRepositories(login: String): Single<List<GithubUserRepository>>

    fun getRepository(login: String, repositoryName: String): Single<GithubUserRepository>
}