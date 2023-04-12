package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ScreenView : MvpView {
    fun showToast(message: String)
}