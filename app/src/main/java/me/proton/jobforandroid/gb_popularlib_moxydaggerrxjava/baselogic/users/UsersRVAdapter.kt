package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories.UserAvatarRepository
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.MainItemViewBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.extensions.setStartDrawableCircleImageFromUri

class UsersRVAdapter constructor(
    private val presenterUsers: UsersListPresenter,
    private val userAvatarRepository: UserAvatarRepository,
) : RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(MainItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            .apply {
                itemView.setOnClickListener {
                    presenterUsers.itemClickListener?.invoke(this)
                }
            }

    override fun getItemCount() = presenterUsers.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenterUsers.bindView(holder.apply { currentPosition = position })

    inner class ViewHolder(private val binding: MainItemViewBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {

        override var currentPosition = -1

        override fun setLogin(text: String) = with(binding) {
            loginText.text = text
        }

        override fun setAvatar(user: GithubUser) = with(binding) {
            loginText.setStartDrawableCircleImageFromUri(userAvatarRepository.imageBuilder(user))
        }
    }
}