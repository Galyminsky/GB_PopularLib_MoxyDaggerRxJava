package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.CompositeDisposable
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUsersRepo
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UserItemView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UsersView
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    private val compositeDisposable = CompositeDisposable()

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val user = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }

    private fun loadData() {
        compositeDisposable.add(
            usersRepo.getUsers()
                .subscribe { users ->
                    usersListPresenter.users.addAll(users)
                    viewState.updateList()
                }
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}