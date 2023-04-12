package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

    @SuppressLint("StaticFieldLeak")
    object ContextHolder {
        lateinit var context: Context
    }

    companion object Navigation {
        private val cicerone: Cicerone<Router> by lazy {
            Cicerone.create()
        }
        val navigatorHolder get() = cicerone.getNavigatorHolder()
        val router get() = cicerone.router
    }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = applicationContext
        RxJavaPlugins.setErrorHandler { }
    }

}