package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.AppDB

@Entity(
    tableName = AppDB.TABLE_USERS,
    indices = [Index(AppDB.LOGIN)]
)
data class GithubUser(
    @SerializedName("id")
    @ColumnInfo(name = AppDB.USER_ID)
    @PrimaryKey
    val id: Long,

    @SerializedName("login")
    @ColumnInfo(name = AppDB.LOGIN)
    var login: String,

    @SerializedName("avatar_url")
    @ColumnInfo(name = AppDB.AVATAR_URL)
    val avatarUrl: String? = null,
)
