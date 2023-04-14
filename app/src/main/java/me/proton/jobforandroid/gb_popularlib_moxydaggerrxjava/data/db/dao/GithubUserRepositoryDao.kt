package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.AppDB
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUserRepository

@Dao
interface GithubUserRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<GithubUserRepository>)

    @Query(
        "select r.* " +
                "from ${AppDB.TABLE_USER_REPOSITORIES} r, " +
                "${AppDB.TABLE_USERS} u " +
                "where r.${AppDB.USER_ID} = u.${AppDB.USER_ID} " +
                "and u.${AppDB.LOGIN} = :userLogin"
    )
    fun getRepositories(userLogin: String): Single<List<GithubUserRepository>>

    @Query(
        "select r.* " +
                "from ${AppDB.TABLE_USER_REPOSITORIES} r, " +
                "${AppDB.TABLE_USERS} u " +
                "where r.${AppDB.NAME} = :repositoryName " +
                "and r.${AppDB.USER_ID} = u.${AppDB.USER_ID} " +
                "and u.${AppDB.LOGIN} = :userLogin"
    )
    fun getRepositoryOnUserLogin(
        userLogin: String,
        repositoryName: String,
    ): Single<GithubUserRepository>

}