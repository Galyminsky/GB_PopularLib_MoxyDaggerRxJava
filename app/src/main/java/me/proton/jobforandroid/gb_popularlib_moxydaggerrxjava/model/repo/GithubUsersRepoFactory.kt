package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.repo

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource.CacheUsersDataSourceFactory
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource.UsersDataSourceFactory

object GithubUsersRepoFactory {
    fun create(): IGithubUsersRepo = GithubUsersRepo(
        UsersDataSourceFactory.create(),
        CacheUsersDataSourceFactory.create()
    )
}