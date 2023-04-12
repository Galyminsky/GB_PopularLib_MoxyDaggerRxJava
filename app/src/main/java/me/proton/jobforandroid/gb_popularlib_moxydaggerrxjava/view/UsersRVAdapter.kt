package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ViewItemUserBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.presenter.IUserListPresenter

class UsersRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewItemUserBinding) :
        RecyclerView.ViewHolder(binding.root), UserItemView {

        override fun setUser(login: String, avatar_url: String) = with(binding) {
            tvLogin.text = login
            tvLogin.setStartDrawableCircleImageFromUri(avatar_url)
        }

        override var pos = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ViewItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()
}