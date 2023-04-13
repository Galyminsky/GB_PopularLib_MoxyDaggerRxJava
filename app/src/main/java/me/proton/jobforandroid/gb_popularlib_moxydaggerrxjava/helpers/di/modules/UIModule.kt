package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.main.MainActivity
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.user.UserFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.userrepositories.RepositoryFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.users.UsersFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert.ImageCompressorFragment

@Module
interface UIModule {

    @ContributesAndroidInjector
    fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun bindUserFragment(): UserFragment

    @ContributesAndroidInjector
    fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    fun bindRepositoryFragment(): RepositoryFragment

    @ContributesAndroidInjector
    fun bindImageCompressorFragment(): ImageCompressorFragment
}