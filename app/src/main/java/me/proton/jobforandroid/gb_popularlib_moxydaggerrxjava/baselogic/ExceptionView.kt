package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic

import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface ExceptionView {
    fun showException(throwable: Throwable)
}