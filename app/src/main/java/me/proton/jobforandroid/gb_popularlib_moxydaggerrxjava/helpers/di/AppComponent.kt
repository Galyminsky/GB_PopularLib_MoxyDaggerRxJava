package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules.ApiModule
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules.DBModule
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules.UIModule
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.modules.UserModule
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules.AppSchedulers
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        UserModule::class,
        ApiModule::class,
        DBModule::class,
        UIModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigationHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withAppScheduler(appSchedulers: AppSchedulers): Builder

        fun build(): AppComponent
    }
}