package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.user

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.ScreenView
import moxy.viewstate.strategy.alias.SingleState

interface UserView : ScreenView {

    @SingleState
    fun showUser(user: GitHubUserViewModel)
}