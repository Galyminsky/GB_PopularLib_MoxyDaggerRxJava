package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.users.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ViewUserBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.click
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.setStartDrawableCircleImageFromUri
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserViewModel

class UserViewHolder(view: View) : ViewHolder(view) {

    private val viewBinding: ViewUserBinding by viewBinding()

    fun bind(user: GitHubUserViewModel, delegate: UsersAdapter.Delegate?) {
        with(viewBinding) {
            userLogin.setStartDrawableCircleImageFromUri(user.avatar)
            userLogin.text = user.login

            root.click { delegate?.onUserPicked(user) }
        }
    }
}