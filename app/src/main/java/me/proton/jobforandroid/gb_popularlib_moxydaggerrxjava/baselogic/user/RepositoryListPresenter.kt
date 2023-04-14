package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.user

import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.ListPresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.userrepositories.RepositoryItemView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUserRepository

class RepositoryListPresenter : ListPresenter<RepositoryItemView> {
    val repositories = mutableListOf<GithubUserRepository>()

    override var itemClickListener: ((RepositoryItemView) -> Unit)? = null

    override fun getCount() = repositories.size

    override fun bindView(view: RepositoryItemView) {
        val repository = repositories[view.currentPosition]
        view.setName(repository.fullName)
    }
}