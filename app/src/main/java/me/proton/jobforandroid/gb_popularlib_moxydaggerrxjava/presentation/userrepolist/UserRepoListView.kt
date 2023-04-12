package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserRepoViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.ScreenView
import moxy.viewstate.strategy.alias.SingleState

interface UserRepoListView : ScreenView {

    @SingleState
    fun showUserRepo(user: List<GitHubUserRepoViewModel>)

}