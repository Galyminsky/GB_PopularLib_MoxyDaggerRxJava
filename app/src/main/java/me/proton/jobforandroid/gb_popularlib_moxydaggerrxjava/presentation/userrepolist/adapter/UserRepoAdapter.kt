package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.userrepolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserRepoViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R.layout.view_user_repo_item

class UserRepoAdapter(private val delegate: Delegate?) :
    ListAdapter<GitHubUserRepoViewModel, UserRepoViewHolder>(UserDiff) {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * пользователя из списка.
         * @param user пользователь
         */
        fun onUserPickedRepo(userRepo: GitHubUserRepoViewModel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder =
        UserRepoViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(view_user_repo_item, parent, false)
        )

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}