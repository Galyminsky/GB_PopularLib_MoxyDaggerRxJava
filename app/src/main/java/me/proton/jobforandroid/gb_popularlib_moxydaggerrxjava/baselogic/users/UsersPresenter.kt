package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.kotlin.plusAssign
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.BasePresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.GithubUsersRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules.AppSchedulers
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.screens.ImageCompressorScreen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.screens.UserScreen

class UsersPresenter constructor(
    private val userRepository: GithubUsersRepository,
    private val appSchedulers: AppSchedulers,
    router: Router,
) : BasePresenter<UsersView>(router) {

    class UsersUserListPresenter : UsersListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.currentPosition]
            view.setLogin(user.login)
            view.setAvatar(user)
        }
    }

    val usersListPresenter = UsersUserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        disposables +=
            userRepository
                .getUsers()
                .observeOn(appSchedulers.main())
                .subscribeOn(appSchedulers.background())
                .subscribe(
                    { gitHubUsers ->
                        usersListPresenter.users.addAll(gitHubUsers)
                        viewState.updateList()
                    },
                    { exception -> viewState.showException(exception) }
                )

        usersListPresenter.itemClickListener = {
            val userLogin = usersListPresenter.users[it.currentPosition].login
            router.navigateTo(UserScreen(userLogin))
        }
    }

    fun openWinImageCompressor() {
        router.navigateTo(ImageCompressorScreen())
    }
}