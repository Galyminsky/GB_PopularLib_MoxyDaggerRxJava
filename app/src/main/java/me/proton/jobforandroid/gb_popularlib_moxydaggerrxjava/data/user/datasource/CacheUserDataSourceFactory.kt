package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.GitHubStorageFactory

object CacheUserDataSourceFactory {

    fun create(): CacheUserDataSource =
        CacheUserDataSourceImpl(
            GitHubStorageFactory.create(App.ContextHolder.context)
        )
}