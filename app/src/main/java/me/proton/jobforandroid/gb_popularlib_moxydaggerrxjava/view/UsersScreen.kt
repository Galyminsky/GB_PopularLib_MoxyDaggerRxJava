package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen {
    fun create() = FragmentScreen { UsersFragment.newInstance() }
}