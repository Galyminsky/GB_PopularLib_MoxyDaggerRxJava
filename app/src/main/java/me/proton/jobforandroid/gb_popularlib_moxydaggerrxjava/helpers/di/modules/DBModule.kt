package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.AppDB
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.AppDataBase
import javax.inject.Singleton

@Module
class DBModule {

    @AppDataBase
    @Singleton
    @Provides
    fun provideAppDB(context: Context): AppDB =
        Room.databaseBuilder(context, AppDB::class.java, AppDB.DB_NAME)
            .build()

}