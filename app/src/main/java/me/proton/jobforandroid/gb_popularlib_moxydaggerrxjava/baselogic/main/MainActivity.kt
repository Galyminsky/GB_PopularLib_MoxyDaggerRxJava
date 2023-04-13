package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.main

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.MainActivityBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.BaseDaggerActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : BaseDaggerActivity(), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val binding: MainActivityBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val presenter by moxyPresenter { MainPresenter(router) }
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        presenter.execExampleMap()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backPressed()
    }
}