package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.user

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.ExceptionView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.SingleState

@SingleState
interface UserView : MvpView, ExceptionView {

    fun init()

    fun showUser(user: GithubUser, requestBuilder: RequestBuilder<Drawable>)

    fun updateRepositoryList()
}