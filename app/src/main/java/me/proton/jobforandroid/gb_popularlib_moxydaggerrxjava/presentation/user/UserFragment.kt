package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.user

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App.Navigation.router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.user.GitHubUserRepositoryFactory
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ViewUserBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.arguments
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.setStartDrawableCircleImageFromUri
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.scheduler.SchedulersFactory
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R.layout.view_user

class UserFragment : MvpAppCompatFragment(view_user), UserView {

    private lateinit var userBundle: GitHubUserViewModel

    companion object Factory {

        private const val ARG_USER_LOGIN = "arg_user_login"

        fun newInstance(userId: String): Fragment =
            UserFragment()
                .arguments(ARG_USER_LOGIN to userId)

    }

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    @Suppress("unused")
    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            userLogin = userLogin,
            router = router,
            userRepository = GitHubUserRepositoryFactory.create(),
            schedulers = SchedulersFactory.create()
        )
    }

    private val viewBinding: ViewUserBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.userLogin.setOnClickListener {
            presenter.displayUserRepos(userBundle)
        }

    }

    override fun showUser(user: GitHubUserViewModel) {
        viewBinding.userLogin.setStartDrawableCircleImageFromUri(user.avatar)
        viewBinding.userLogin.text = user.login
        userBundle = user
    }

    override fun showError(error: Throwable) {
        Toast.makeText(requireContext(), "User Fragment", Toast.LENGTH_LONG).show()
    }
}