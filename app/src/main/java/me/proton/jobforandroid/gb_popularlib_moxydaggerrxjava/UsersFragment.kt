package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.adapters.UsersRVAdapter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.FragmentUsersBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.model.GithubUsersRepo
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.UsersPresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router, AndroidScreens())
    }
    private var adapter: UsersRVAdapter? = null

    private var vb: FragmentUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}