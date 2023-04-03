package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.counters

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountersView : MvpView {
    fun setTextFirstCounter(text: String)
    fun setTextSecondCounter(text: String)
    fun setTextThirdCounter(text: String)
}