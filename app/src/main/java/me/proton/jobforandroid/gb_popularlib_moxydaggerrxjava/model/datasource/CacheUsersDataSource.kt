package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource

import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUser

interface CacheUsersDataSource : UsersDataSource {
    fun retain(users: List<GithubUser>): Single<List<GithubUser>>
    fun retain(user: GithubUser): Single<GithubUser>
}