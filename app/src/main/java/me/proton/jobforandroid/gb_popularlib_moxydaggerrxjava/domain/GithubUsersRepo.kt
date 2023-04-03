package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain

import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
}