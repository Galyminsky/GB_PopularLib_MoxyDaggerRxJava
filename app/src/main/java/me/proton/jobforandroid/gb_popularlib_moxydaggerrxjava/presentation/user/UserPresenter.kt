package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.user

import com.github.terrakok.cicerone.Router
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.GitHubUserRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist.UserRepoListScreen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.scheduler.Schedulers
import moxy.MvpPresenter

class UserPresenter(
    private val userLogin: String,
    private val router: Router,
    private val userRepository: GitHubUserRepository,
    private val schedulers: Schedulers,
) : MvpPresenter<UserView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        disposables +=
            userRepository
                .getUserByLogin(userLogin)
                .map(GitHubUserViewModel.Mapper::map)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showUser,
                    viewState::showError
                )
    }

    fun displayUserRepos(user: GitHubUserViewModel) {
        router.navigateTo(UserRepoListScreen(user.repos_url))
    }

    override fun onDestroy() {
        disposables.clear()
    }
}