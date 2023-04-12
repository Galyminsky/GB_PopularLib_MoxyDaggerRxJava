package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.api.GitHubApiFactory

object UserDataSourceFactory {

    fun create(): UserDataSource = CloudUserDataSource(GitHubApiFactory.create())
}