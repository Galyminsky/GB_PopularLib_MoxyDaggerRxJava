package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "user_repos",
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class RoomGithubRepository(
    @PrimaryKey
    @SerializedName("repos_id")
    val id: Int,
    @ColumnInfo(name = "repos_name")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "repos_description")
    @SerializedName("description")
    val description: String?,
    @ColumnInfo(name = "repos_lang")
    @SerializedName("language")
    val language: String,
    @ColumnInfo(name = "fork_cpunt")
    @SerializedName("forks_count")
    val forks_count: Int,
    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    var userId: String,
)