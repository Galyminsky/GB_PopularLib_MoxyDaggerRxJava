package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.datasource

import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.api.GithubApi
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUserRepo

class UsersDataSourceImpl(private val githubApi: GithubApi) : UsersDataSource {
    override fun getUsers(): Single<List<GithubUser>> = githubApi.getUsers()

    override fun getUserByLogin(login: String?): Maybe<GithubUser> {
        return if (login != null) {
            githubApi.getUserByLogin(login)
                .toMaybe()
        } else {
            Maybe.empty()
        }

    }

    override fun getUserRepos(url: String): Maybe<List<GithubUserRepo>> =
        githubApi.getUserRepos(url).toMaybe()

}
