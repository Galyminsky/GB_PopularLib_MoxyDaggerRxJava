package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.room

import android.content.Context
import androidx.room.Room

object GitHubStorageFactory {

    fun create(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "github.db")
            .build()

    fun createInMemory(context: Context): Database =
        Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .fallbackToDestructiveMigration()
            .build()
}