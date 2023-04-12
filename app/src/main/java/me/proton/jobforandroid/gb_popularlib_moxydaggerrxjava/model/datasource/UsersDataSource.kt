package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUserRepo

interface UsersDataSource {

    fun getUsers(): Single<List<GithubUser>>
    fun getUserByLogin(login: String?): Maybe<GithubUser>
    fun getUserRepos(url: String): Maybe<List<GithubUserRepo>>

}