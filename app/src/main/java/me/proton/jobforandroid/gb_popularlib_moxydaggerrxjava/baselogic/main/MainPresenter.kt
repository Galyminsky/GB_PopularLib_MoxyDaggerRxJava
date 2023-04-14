package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.main

import android.annotation.SuppressLint
import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.BasePresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.screens.UsersScreen
import java.util.*
import java.util.concurrent.TimeUnit

class MainPresenter(router: Router) : BasePresenter<MainView>(router) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreen())
    }

    @SuppressLint("CheckResult")
    fun execExampleMap() {
        Observable.fromIterable(listOf("1", "2", "3", "4", "5"))
            .switchMap {
                val delay = Random().nextInt(1000).toLong()
                return@switchMap Observable.just(it + "x").delay(
                    delay,
                    TimeUnit.MILLISECONDS
                )
            }
            .subscribe(
                { s -> Log.d("My", "onNext: $s") },
                { Log.d("My", "onError: ${it.message}") }
            )
    }
}