package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UsersRepo


@Suppress("UNCHECKED_CAST")
class UsersViewModelFactory(private val repo: UsersRepo) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(repo) as T
    }
}