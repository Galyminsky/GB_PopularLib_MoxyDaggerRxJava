package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ItemUsersBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UserEntity

class UsersViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
) {
    private val binding = ItemUsersBinding.bind(itemView)

    fun bind(userEntity: UserEntity, listener: ((UserEntity) -> Unit)?) {
        with(binding) {
            userItem.setOnClickListener {
                listener?.invoke(userEntity)
            }
            tvUserId.text = userEntity.id.toString()
            tvUserLogin.text = userEntity.login
            ivUserAvatar.load(userEntity.avatarUrl) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                transformations(RoundedCornersTransformation(16f))
            }
        }
    }
}