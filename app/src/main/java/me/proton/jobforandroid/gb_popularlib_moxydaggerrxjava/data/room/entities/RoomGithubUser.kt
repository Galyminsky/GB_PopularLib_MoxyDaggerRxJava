package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "github_user")
data class RoomGithubUser(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String,
    @ColumnInfo(name = "avatar")
    @SerializedName("avatar_url")
    val avatar: String,
    @ColumnInfo(name = "repos")
    @SerializedName("repos_url")
    val repos_url: String,
    @ColumnInfo(name = "migrate")
    val migrate: String?,
)
