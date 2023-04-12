package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities.RoomGithubUser

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUser>)

    @Update
    fun update(user: RoomGithubUser)

    @Update
    fun update(vararg users: RoomGithubUser)

    @Update
    fun update(users: List<RoomGithubUser>)

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(vararg users: RoomGithubUser)

    @Delete
    fun delete(users: List<RoomGithubUser>)

    @Query("SELECT * FROM github_user")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM github_user")
    fun fetchUsers(): Single<List<RoomGithubUser>>

    @Query("SELECT * FROM github_user WHERE login LIKE :login LIMIT 1")
    fun fetchUserByLogin(login: String): Single<RoomGithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun retain(users: List<RoomGithubUser>): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun retain(user: RoomGithubUser): Completable
}