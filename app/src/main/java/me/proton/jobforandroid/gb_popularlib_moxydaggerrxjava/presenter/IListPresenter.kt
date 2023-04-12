package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}