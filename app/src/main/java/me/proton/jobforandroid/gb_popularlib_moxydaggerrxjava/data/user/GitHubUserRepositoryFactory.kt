package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource.CacheUserDataSourceFactory
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource.UserDataSourceFactory

object GitHubUserRepositoryFactory {

    private val repository: GitHubUserRepository by lazy {
        GitHubUserRepositoryImpl(
            UserDataSourceFactory.create(),
            CacheUserDataSourceFactory.create()
        )
    }

    fun create(): GitHubUserRepository = repository
}