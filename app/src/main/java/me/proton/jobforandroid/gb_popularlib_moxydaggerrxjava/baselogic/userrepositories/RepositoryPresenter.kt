package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.userrepositories

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.plusAssign
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.BasePresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.GithubUsersRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules.AppSchedulers
import javax.inject.Inject

class RepositoryPresenter @Inject constructor(
    private val userRepository: GithubUsersRepository,
    private val appSchedulers: AppSchedulers,
    private val router_: Router,
    private val userLogin: String?,
    private val repositoryName: String?,
) : BasePresenter<RepositoryView>(router_) {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        userLogin?.let { login ->
            repositoryName?.let { repository ->
                disposables += loadRepositoriesInfo(login, repository)
            } ?: viewState.showException(Throwable("Пустое имя репозитория"))
        } ?: viewState.showException(Throwable("Пустой логин пользователя"))
    }

    private fun loadRepositoriesInfo(loin: String, repository: String): Disposable {
        return userRepository
            .getRepository(loin, repository)
            .map { repo ->
                val data = StringBuilder()
                data.append("Имя репозитория: ${repo.name}\n")
                    .append("Полное имя: ${repo.fullName}\n")
                    .append("Кол-во форков: ${repo.countForks}\n")
                    .append("Размер: ${repo.size}\n")
                    .append("Дата создания: ${repo.created}\n")

                data.toString()
            }
            .observeOn(appSchedulers.main())
            .subscribeOn(appSchedulers.background()) //обработку делаем в отдельном потоке
            .subscribe(
                { repositoryInfo -> viewState.showInfo(repositoryInfo) },
                { exception -> viewState.showException(exception) }
            )
    }
}