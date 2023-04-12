package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

interface ScreenView : MvpView {

    @SingleState
    fun showError(error: Throwable)
}