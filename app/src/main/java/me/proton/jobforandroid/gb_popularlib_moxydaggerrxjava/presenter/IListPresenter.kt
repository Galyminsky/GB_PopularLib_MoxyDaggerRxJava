package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.IItemView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>