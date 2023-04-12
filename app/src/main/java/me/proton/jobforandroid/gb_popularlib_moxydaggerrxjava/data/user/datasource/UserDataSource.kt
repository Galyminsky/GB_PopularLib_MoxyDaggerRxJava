package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubUser

interface UserDataSource {

    fun getUsers(): Single<List<RoomGithubUser>>
    fun getUserByLogin(userId: String): Maybe<RoomGithubUser>

    fun getUserListRepo(reposUrl: String): Single<List<RoomGithubRepository>>
}