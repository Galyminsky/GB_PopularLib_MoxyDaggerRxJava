package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presentation.GitHubUserViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R.layout.view_user

class UsersAdapter(private val delegate: Delegate?) :
    ListAdapter<GitHubUserViewModel, UserViewHolder>(UserDiff) {

    interface Delegate {

        /**
         * Событие наступает при выборе
         * пользователя из списка.
         * @param user пользователь
         */
        fun onUserPicked(user: GitHubUserViewModel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(view_user, parent, false)
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}