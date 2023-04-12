package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user

import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubUser

interface GitHubUserRepository {

    fun getUsers(): Observable<List<RoomGithubUser>>

    fun getUserByLogin(userId: String): Maybe<RoomGithubUser>

    fun getUserListRepo(reposUrl: String): Single<List<RoomGithubRepository>>
}