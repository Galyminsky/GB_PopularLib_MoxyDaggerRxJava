package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.repo

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource.CacheUsersDataSource
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource.UsersDataSource
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUserRepo

class GithubUsersRepo(
    private val usersDataSource: UsersDataSource,
    private val cacheUsersDataSource: CacheUsersDataSource,
) : IGithubUsersRepo {

    override fun getUsers(): Observable<List<GithubUser>> =
        Observable.concat(
            cacheUsersDataSource.getUsers().toObservable(),
            usersDataSource.getUsers().flatMap(cacheUsersDataSource::retain).toObservable()
        ).subscribeOn(Schedulers.io())

    override fun getUserByLogin(login: String?): Maybe<GithubUser> =
        cacheUsersDataSource.getUserByLogin(login)
            .switchIfEmpty(usersDataSource.getUserByLogin(login))
            .subscribeOn(Schedulers.io())

    override fun getUserRepos(url: String): Maybe<List<GithubUserRepo>> =
        usersDataSource.getUserRepos(url)
}