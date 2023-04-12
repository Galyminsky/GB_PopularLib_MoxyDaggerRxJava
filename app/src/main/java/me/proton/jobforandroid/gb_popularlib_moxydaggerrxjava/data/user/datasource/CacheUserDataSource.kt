package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource

import io.reactivex.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubUser

interface CacheUserDataSource : UserDataSource {

    fun retain(users: List<RoomGithubUser>): Single<List<RoomGithubUser>>
    fun retain(user: RoomGithubUser): Single<RoomGithubUser>
}