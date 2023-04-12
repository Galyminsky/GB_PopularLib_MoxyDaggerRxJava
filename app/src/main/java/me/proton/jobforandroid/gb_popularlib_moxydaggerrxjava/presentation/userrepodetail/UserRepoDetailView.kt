package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepodetail

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserRepoViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.ScreenView
import moxy.viewstate.strategy.alias.SingleState

interface UserRepoDetailView : ScreenView {
    @SingleState
    fun showForkCount(user: GitHubUserRepoViewModel)
}