package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.users

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun showProgressbar(inProgress: Boolean)
    fun showUsers(users: List<GithubUser>)
    fun showError(throwable: Throwable)
}