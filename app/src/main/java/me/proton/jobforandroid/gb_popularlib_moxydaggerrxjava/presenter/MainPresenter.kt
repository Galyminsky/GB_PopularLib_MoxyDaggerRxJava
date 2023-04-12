package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter

import com.github.terrakok.cicerone.Router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.MainView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UsersScreen
import moxy.MvpPresenter

class MainPresenter(
    private val router: Router,
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(UsersScreen.create())
    }

    fun back() = router.exit()
}