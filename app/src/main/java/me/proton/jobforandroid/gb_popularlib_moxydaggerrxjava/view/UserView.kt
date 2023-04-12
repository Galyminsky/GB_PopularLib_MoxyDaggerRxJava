package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState()
interface UserView : MvpView {
    fun setLogin(text: String)
}