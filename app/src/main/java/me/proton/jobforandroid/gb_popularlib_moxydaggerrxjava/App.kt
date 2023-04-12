package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object Navigation {
        private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
        val navigatorHolder get() = cicerone.getNavigatorHolder()
        val router get() = cicerone.router
    }

}