package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.users

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.ExceptionView
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UsersView : MvpView, ExceptionView {

    fun init()

    fun updateList()
}