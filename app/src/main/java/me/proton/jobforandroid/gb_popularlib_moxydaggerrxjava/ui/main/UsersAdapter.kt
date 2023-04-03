package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.main

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UserEntity

class UsersAdapter() : RecyclerView.Adapter<UsersViewHolder>() {
    private val data = mutableListOf<UserEntity>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(parent)

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(data[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntity>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}
