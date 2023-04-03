package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.app
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.FragmentMainBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UserEntity
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.profile.ProfileFragment

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = UsersAdapter()
    private val viewModel: UsersViewModel by lazy {
        ViewModelProvider(this, UsersViewModelFactory(app.usersRepo))[UsersViewModel::class.java]
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getUsersLivedata().observe(viewLifecycleOwner) {
            showUsers(it)
        }

        viewModel.getErrorLivedata().observe(viewLifecycleOwner) {
            showError(it)
        }
        viewModel.getProgressLivedata().observe(viewLifecycleOwner) {
            showProgressbar(it)
        }
    }


    private fun initViews() {
        with(binding) {
            rvUsersList.layoutManager = LinearLayoutManager(app)
            rvUsersList.adapter = adapter
            btnRefresh.setOnClickListener {
                viewModel.loadData()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
        adapter.listener = {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, ProfileFragment.newInstance(Bundle().apply {
                    putParcelable("USER", it)
                }))
                .addToBackStack("")
                .commit()
        }
    }

    private fun showError(throwable: Throwable) {
        Toast.makeText(app, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun showProgressbar(inProgress: Boolean) {
        with(binding) {
            rvUsersList.isVisible = !inProgress
            progress.isVisible = inProgress
            btnRefresh.isEnabled = !inProgress
        }
    }

}