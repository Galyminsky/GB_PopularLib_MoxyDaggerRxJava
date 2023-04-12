package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepodetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ViewUserBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.arguments
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserRepoViewModel
import moxy.MvpAppCompatFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R.layout.view_user_repo_detail

class UserRepoDetailFragment : MvpAppCompatFragment(view_user_repo_detail), UserRepoDetailView {

    private lateinit var userBundle: GitHubUserRepoViewModel

    private val disposable = CompositeDisposable()

    companion object Factory {

        private const val ARG_USER_LOGIN = "arg_repos_fork"

        fun newInstance(forkCount: String): Fragment =
            UserRepoDetailFragment()
                .arguments(ARG_USER_LOGIN to forkCount)

    }

    private val forkCount: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val viewBinding: ViewUserBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onShowForkCount()
    }

    private fun onShowForkCount() {

        val disposableSaveData = Completable.fromAction {
            viewBinding.userLogin.text = forkCount
        }
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(io.reactivex.schedulers.Schedulers.newThread())
            .subscribe(

            )
        disposable.add(disposableSaveData)

    }

    override fun showForkCount(user: GitHubUserRepoViewModel) {
        viewBinding.userLogin.text = user.forks_count.toString()
        userBundle = user
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), error.message, Toast.LENGTH_LONG).show()
    }
}