package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserRepoViewModel

object UserDiff : DiffUtil.ItemCallback<GitHubUserRepoViewModel>() {

    private val payload = Any()

    override fun areItemsTheSame(
        oldItem: GitHubUserRepoViewModel,
        newItem: GitHubUserRepoViewModel,
    ): Boolean {
        return oldItem.name == newItem.name
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: GitHubUserRepoViewModel,
        newItem: GitHubUserRepoViewModel,
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(
        oldItem: GitHubUserRepoViewModel,
        newItem: GitHubUserRepoViewModel,
    ) = payload

}

