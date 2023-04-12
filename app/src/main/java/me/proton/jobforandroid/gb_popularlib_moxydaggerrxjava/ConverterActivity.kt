package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App.Navigation.navigatorHolder
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App.Navigation.router
import moxy.MvpAppCompatActivity

class ConverterActivity : MvpAppCompatActivity() {

    private val navigator = AppNavigator(this, android.R.id.content)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: router.newRootScreen(ConverterScreen)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}