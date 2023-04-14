package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.cache

import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.AppDB
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUserRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.AppDataBase
import javax.inject.Inject

class GithubCacheImpl @Inject constructor(@AppDataBase private val appDB: AppDB) : GithubCache {

    override fun insertUsers(users: List<GithubUser>) {
        appDB.githubUserDao().insert(users)
    }

    override fun getUsers(): Single<List<GithubUser>> = appDB.githubUserDao().getUsers()

    override fun getUser(userLogin: String): Single<GithubUser> =
        appDB.githubUserDao().getUser(userLogin)

    override fun insertRepository(repository: GithubUserRepository) {
        appDB.githubUserRepositoryDao().insert(listOf(repository))
    }

    override fun insertRepositories(repositories: List<GithubUserRepository>) {
        appDB.githubUserRepositoryDao().insert(repositories)
    }

    override fun getRepositoriesOnUserLogin(userLogin: String): Single<List<GithubUserRepository>> =
        appDB.githubUserRepositoryDao().getRepositories(userLogin)

    override fun getRepositoryOnUserLogin(
        userLogin: String,
        repositoryName: String,
    ): Single<GithubUserRepository> =
        appDB.githubUserRepositoryDao().getRepositoryOnUserLogin(userLogin, repositoryName)
}