package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava


import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


import kotlinx.coroutines.withContext
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules.AppSchedulersImpl


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App> =
        DaggerApplication
            .builder()
            .apply {
                withContext(applicationContext)
                val cicerone = Cicerone.create()
                withNavigationHolder(cicerone.getNavigatorHolder())
                withRouter(cicerone.router)
                withAppScheduler(AppSchedulersImpl())
            }
            .build()
}
