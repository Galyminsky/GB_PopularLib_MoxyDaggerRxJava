package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ViewUserRepoItemBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.click
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ext.setStartDrawableCircleImageFromUri
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserRepoViewModel

class UserRepoViewHolder(view: View) : ViewHolder(view) {

    private val viewBinding: ViewUserRepoItemBinding by viewBinding()

    fun bind(user: GitHubUserRepoViewModel, delegate: UserRepoAdapter.Delegate?) {
        with(viewBinding) {
            userLogin.setStartDrawableCircleImageFromUri(user.name)
            userLogin.text = user.name
            root.click { delegate?.onUserPickedRepo(user) }
        }
    }

}