package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource

object CacheUsersDataSourceFactory {
    fun create(): CacheUsersDataSource = CacheUsersDataSourceImpl()
}