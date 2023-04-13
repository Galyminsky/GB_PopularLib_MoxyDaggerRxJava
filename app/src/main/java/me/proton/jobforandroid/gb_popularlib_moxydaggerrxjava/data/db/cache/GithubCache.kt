package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.cache

import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUserRepository

interface GithubCache {

    fun insertUsers(users: List<GithubUser>)

    fun getUsers(): Single<List<GithubUser>>

    fun getUser(userLogin: String): Single<GithubUser>

    fun insertRepository(repository: GithubUserRepository)

    fun insertRepositories(repositories: List<GithubUserRepository>)

    fun getRepositoriesOnUserLogin(userLogin: String): Single<List<GithubUserRepository>>

    fun getRepositoryOnUserLogin(
        userLogin: String,
        repositoryName: String,
    ): Single<GithubUserRepository>
}