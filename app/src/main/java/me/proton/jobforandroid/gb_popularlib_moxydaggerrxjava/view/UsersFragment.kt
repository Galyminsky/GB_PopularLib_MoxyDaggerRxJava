package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import android.annotation.SuppressLint
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.App.Navigation.router
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.FragmentUsersBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.repo.GithubUsersRepoFactory
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val binding: FragmentUsersBinding by viewBinding()
    private val presenter by moxyPresenter {
        UsersPresenter(
            GithubUsersRepoFactory.create(),
            router
        )
    }
    private var adapter: UsersRVAdapter? = null

    override fun init() {
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        binding.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed() = presenter.backPressed()
}