package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.userrepositories

interface RepositoryItemView {
    var currentPosition: Int

    fun setName(text: String)
}