package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource.CacheUserDataSource
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource.UserDataSource

class GitHubUserRepositoryImpl(
    private val cloud: UserDataSource,
    private val cache: CacheUserDataSource,
) : GitHubUserRepository {

    override fun getUsers(): Observable<List<RoomGithubUser>> =
        Observable.merge(
            cache.getUsers().toObservable(),
            cloud.getUsers().flatMap(cache::retain).toObservable()
        )

    override fun getUserByLogin(userId: String): Maybe<RoomGithubUser> =
        cache.getUserByLogin(userId)
            .onErrorResumeNext(
                cloud.getUserByLogin(userId)
            )

    override fun getUserListRepo(userId: String): Single<List<RoomGithubRepository>> =
        cloud.getUserListRepo(userId)
}