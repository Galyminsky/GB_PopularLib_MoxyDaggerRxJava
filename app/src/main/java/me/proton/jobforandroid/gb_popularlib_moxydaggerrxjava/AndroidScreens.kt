package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.IScreens

class AndroidScreens : IScreens {
    override fun users() = UsersScreen()
    override fun user(user: GithubUser) = UserScreen(user)
}