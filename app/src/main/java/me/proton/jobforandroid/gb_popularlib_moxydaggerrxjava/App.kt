package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.OurUsersRepoImpl
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.GithubUsersRepo
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.cicerone.CiceronePresenter

class App : Application() {
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val navigation get() = CiceronePresenter()

    val usersRepo: GithubUsersRepo by lazy { OurUsersRepoImpl() }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}
