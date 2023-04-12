package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.api.GithubApiFactory

object UsersDataSourceFactory {
    fun create() = UsersDataSourceImpl(GithubApiFactory.create())
}