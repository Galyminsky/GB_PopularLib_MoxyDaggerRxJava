package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity

import androidx.room.*
import com.google.gson.annotations.SerializedName
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.AppDB

@Entity(
    tableName = AppDB.TABLE_USER_REPOSITORIES,
    indices = [Index(AppDB.USER_ID)],
    foreignKeys = [ForeignKey(
        entity = GithubUser::class,
        parentColumns = [AppDB.USER_ID],
        childColumns = [AppDB.USER_ID],
        onDelete = ForeignKey.CASCADE
    )]
)
data class GithubUserRepository(
    @SerializedName("id")
    @ColumnInfo(name = AppDB.REPOSITORY_ID)
    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = AppDB.USER_ID)
    var userId: Long,

    @SerializedName("name")
    @ColumnInfo(name = AppDB.NAME)
    var name: String,

    @SerializedName("full_name")
    @ColumnInfo(name = AppDB.FULL_NAME)
    val fullName: String,

    @SerializedName("forks")
    @ColumnInfo(name = AppDB.FORKS)
    val countForks: Int,

    @SerializedName("size")
    @ColumnInfo(name = AppDB.SIZE)
    val size: Int,

    @SerializedName("created_at")
    @ColumnInfo(name = AppDB.CREATED)
    val created: String,
) {
    @Ignore
    var owner: GithubUserRepositoryOwner? = null
}
