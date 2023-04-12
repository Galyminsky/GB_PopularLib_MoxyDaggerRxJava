package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter

import com.github.terrakok.cicerone.Router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.MainView
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }

}