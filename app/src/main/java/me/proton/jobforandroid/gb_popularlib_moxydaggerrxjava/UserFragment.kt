package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.FragmentUserBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.UserPresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UserView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(R.layout.fragment_user), UserView, BackButtonListener {

    private val vb: FragmentUserBinding by viewBinding()
    private val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        UserPresenter(App.instance.router, user)
    }

    override fun setLogin(text: String) {
        vb.userLoginText.text = text
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        private const val USER = "USER"
        fun newInstance(user: GithubUser): Fragment =
            UserFragment().apply {
                arguments = bundleOf().apply {
                    putParcelable(USER, user)
                }
            }
    }
}