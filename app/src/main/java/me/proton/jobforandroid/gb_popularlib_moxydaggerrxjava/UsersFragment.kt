package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.adapters.UsersRVAdapter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.FragmentUsersBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUsersRepo
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.UsersPresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(R.layout.fragment_users), UsersView,
    BackButtonListener {

    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens())
    }
    private var adapter: UsersRVAdapter? = null

    private val vb: FragmentUsersBinding by viewBinding()

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}