package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.IScreens

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user) }
}