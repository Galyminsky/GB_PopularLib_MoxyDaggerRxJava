package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import com.github.terrakok.cicerone.androidx.AppNavigator
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App.Navigation.navigatorHolder
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App.Navigation.router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    private val presenter by moxyPresenter { MainPresenter(router) }
    private val navigator = AppNavigator(this, R.id.container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.back()
    }
}