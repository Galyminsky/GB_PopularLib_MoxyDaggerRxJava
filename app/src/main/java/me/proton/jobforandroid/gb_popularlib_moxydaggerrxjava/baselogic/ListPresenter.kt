package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic

interface ListPresenter<T> {

    var itemClickListener: ((T) -> Unit)?

    fun bindView(view: T)

    fun getCount(): Int
}