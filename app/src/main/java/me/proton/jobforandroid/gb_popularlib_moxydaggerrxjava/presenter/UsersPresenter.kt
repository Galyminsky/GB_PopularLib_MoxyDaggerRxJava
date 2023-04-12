package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.repo.IGithubUsersRepo
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.GithubUserViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UserItemView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UserScreen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UsersView
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: IGithubUsersRepo,
    private val router: Router,
) : MvpPresenter<UsersView>() {

    private val disposables: CompositeDisposable = CompositeDisposable()

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUserViewModel>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setUser(user.login, user.avatar_url)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val login = usersListPresenter.users[itemView.pos].login
            router.navigateTo(UserScreen(login).create())
        }
    }

    private fun loadData() {
        disposables +=
            usersRepo.getUsers()
                .observeOn(Schedulers.computation())
                .map { users ->
                    val list = mutableListOf<GithubUserViewModel>()
                    for (i in users.indices) {
                        list.add(GithubUserViewModel.Mapper.map(users[i]))
                    }
                    list
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { users ->
                        usersListPresenter.users.addAll(users)
                        viewState.updateList()
                    },
                    { error ->
                        viewState.showToast(error.message ?: "Load users error")
                    }
                )

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}