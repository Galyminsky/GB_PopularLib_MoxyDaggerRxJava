package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.users

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.GitHubUserRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.user.UserScreen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.scheduler.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val userRepository: GitHubUserRepository,
    private val router: Router,
    private val schedulers: Schedulers,
) : MvpPresenter<UsersView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            userRepository
                .getUsers()
                .observeOn(schedulers.background())
                .map { users -> users.map(GitHubUserViewModel.Mapper::map) }
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showUsers,
                    viewState::showError
                )
    }

    fun displayUser(user: GitHubUserViewModel) {
        router.navigateTo(UserScreen(user.login))
    }

    override fun onDestroy() {
        disposables.dispose()
    }
}