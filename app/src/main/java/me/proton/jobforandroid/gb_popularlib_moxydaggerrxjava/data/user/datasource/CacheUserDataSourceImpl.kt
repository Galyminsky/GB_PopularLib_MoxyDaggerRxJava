package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.datasource

import io.reactivex.Maybe
import io.reactivex.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.Database
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubUser

class CacheUserDataSourceImpl(private val database: Database) : CacheUserDataSource {

    override fun getUsers(): Single<List<RoomGithubUser>> =
        database
            .userDao
            .fetchUsers()

    override fun getUserByLogin(userId: String): Maybe<RoomGithubUser> =
        database
            .userDao
            .fetchUserByLogin(userId)
            .toMaybe()

    override fun getUserListRepo(reposUrl: String): Single<List<RoomGithubRepository>> =
        database
            .repositoryDao
            .getUserListRepo(reposUrl)

    override fun retain(users: List<RoomGithubUser>): Single<List<RoomGithubUser>> =
        database
            .userDao
            .retain(users)
            .andThen(getUsers())

    override fun retain(user: RoomGithubUser): Single<RoomGithubUser> =
        database
            .userDao
            .retain(user)
            .andThen(getUserByLogin(user.id))
            .toSingle()
}