package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserRepoListScreen(private val reposUrl: String) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment =
        UserRepoListFragment.newInstance(reposUrl)
}