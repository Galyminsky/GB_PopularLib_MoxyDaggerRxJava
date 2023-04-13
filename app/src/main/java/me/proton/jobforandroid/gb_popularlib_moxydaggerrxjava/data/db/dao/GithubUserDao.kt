package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.AppDB
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser

@Dao
interface GithubUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<GithubUser>)

    @Query("select * from ${AppDB.TABLE_USERS}")
    fun getUsers(): Single<List<GithubUser>>

    @Query("select * from ${AppDB.TABLE_USERS} where ${AppDB.LOGIN} = :userLogin")
    fun getUser(userLogin: String): Single<GithubUser>

}