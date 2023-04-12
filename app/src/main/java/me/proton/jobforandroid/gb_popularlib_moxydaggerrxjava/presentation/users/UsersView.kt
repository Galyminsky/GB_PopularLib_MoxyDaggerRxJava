package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.users

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.ScreenView
import moxy.viewstate.strategy.alias.SingleState

interface UsersView : ScreenView {

    /**
     * Показывает список пользователей.
     * @param users список пользователей
     */
    @SingleState
    fun showUsers(users: List<GitHubUserViewModel>)
}