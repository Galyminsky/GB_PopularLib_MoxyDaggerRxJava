package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.userrepositories

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.ExceptionView
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface RepositoryView : MvpView, ExceptionView {

    fun showInfo(text: String)
}