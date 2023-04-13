package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.users

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser

interface UserItemView {
    var currentPosition: Int

    fun setLogin(text: String)

    fun setAvatar(user: GithubUser)
}