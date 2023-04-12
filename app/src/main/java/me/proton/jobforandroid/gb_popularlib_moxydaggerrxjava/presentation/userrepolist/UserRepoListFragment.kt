package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App.Navigation.router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.GitHubUserRepositoryFactory
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ViewUserRepoListBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.arguments
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserRepoViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist.adapter.UserRepoAdapter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.scheduler.SchedulersFactory
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R.layout.view_user_repo_list

class UserRepoListFragment : MvpAppCompatFragment(view_user_repo_list), UserRepoListView,
    UserRepoAdapter.Delegate {

    companion object Factory {

        private const val ARG_USER_LOGIN = "arg_user_repos_list"

        fun newInstance(reposUrl: String): Fragment =
            UserRepoListFragment()
                .arguments(ARG_USER_LOGIN to reposUrl)

    }

    private val reposUrl: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val presenter: UserRepoListPresenter by moxyPresenter {
        UserRepoListPresenter(
            repos_url = reposUrl,
            router = router,
            userRepository = GitHubUserRepositoryFactory.create(),
            schedulers = SchedulersFactory.create()
        )
    }

    private val viewBinding: ViewUserRepoListBinding by viewBinding()
    private val userRepoAdapter = UserRepoAdapter(delegate = this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.users.adapter = userRepoAdapter
    }

    override fun showUserRepo(user: List<GitHubUserRepoViewModel>) {
        userRepoAdapter.submitList(user)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), "Какая то ошибка", Toast.LENGTH_LONG).show()
    }

    override fun onUserPickedRepo(userRepo: GitHubUserRepoViewModel) {
        presenter.displayUserRepoDetail(userRepo)
    }
}