package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules

import dagger.Binds
import dagger.Module
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert.ImageConverter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert.ImageConverterImpl
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.cache.GithubCache
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.db.cache.GithubCacheImpl
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.GithubUsersRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.GithubUsersRepositoryImpl
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.UserAvatarRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.UserAvatarRepositoryImpl
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.NetworkStatus
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.NetworkStatusImpl
import javax.inject.Singleton

@Module
interface UserModule {

    @Singleton
    @Binds
    fun bindGithubUsersRepository(repository: GithubUsersRepositoryImpl): GithubUsersRepository

    @Singleton
    @Binds
    fun bindGithubCache(githubCache: GithubCacheImpl): GithubCache

    @Singleton
    @Binds
    fun bindNetworkStatus(networkStatus: NetworkStatusImpl): NetworkStatus

    @Binds
    fun bindImageConverter(imageConverter: ImageConverterImpl): ImageConverter

    @Singleton
    @Binds
    fun bindUserAvatarRepository(userAvatarRepository: UserAvatarRepositoryImpl): UserAvatarRepository

}