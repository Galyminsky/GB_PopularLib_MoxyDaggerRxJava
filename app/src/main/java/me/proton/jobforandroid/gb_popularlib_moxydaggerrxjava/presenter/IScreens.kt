package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter

import com.github.terrakok.cicerone.Screen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUser

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}