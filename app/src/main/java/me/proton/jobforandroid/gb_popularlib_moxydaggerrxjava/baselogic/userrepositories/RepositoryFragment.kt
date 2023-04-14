package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.userrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.GithubUsersRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.RepositoryFragmentBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.extensions.showToast
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.BaseDaggerFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules.AppSchedulers
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepositoryFragment : BaseDaggerFragment(), RepositoryView, BackButtonListener {

    companion object {
        const val USER_LOGIN = "userLogin"
        const val REPOSITORY_NAME = "repositoryName"
        fun newInstance(userLogin: String, repositoryName: String): Fragment = RepositoryFragment()
            .also {
                it.arguments = bundleOf(
                    USER_LOGIN to userLogin,
                    REPOSITORY_NAME to repositoryName
                )
            }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appSchedulers: AppSchedulers

    @Inject
    lateinit var githubUsersRepository: GithubUsersRepository

    private val userLogin by lazy { arguments?.getString(USER_LOGIN) }
    private val repositoryName by lazy { arguments?.getString(REPOSITORY_NAME) }
    private val binding: RepositoryFragmentBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private val presenter by moxyPresenter {
        RepositoryPresenter(
            githubUsersRepository,
            appSchedulers,
            router,
            userLogin,
            repositoryName
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) =
        binding.root

    override fun showInfo(text: String) {
        binding.infoText.text = text
    }

    override fun showException(throwable: Throwable) {
        context?.showToast(throwable.message)
    }

    override fun backPressed() = presenter.backPressed()
}