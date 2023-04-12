package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.repo

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUserRepo

interface IGithubUsersRepo {
    fun getUsers(): Observable<List<GithubUser>>
    fun getUserByLogin(login: String?): Maybe<GithubUser>
    fun getUserRepos(url: String): Maybe<List<GithubUserRepo>>
}