package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.entity.GithubUserRepo
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : ScreenView {
    fun showUser(user: GithubUserViewModel)
    fun updateRepos()
    fun init()
    fun showRepo(repo: GithubUserRepo)
}