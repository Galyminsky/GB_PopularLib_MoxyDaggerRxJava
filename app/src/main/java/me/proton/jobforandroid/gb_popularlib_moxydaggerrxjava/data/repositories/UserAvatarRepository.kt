package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories

import android.graphics.drawable.Drawable
import com.bumptech.glide.RequestBuilder
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser

interface UserAvatarRepository {

    fun imageBuilder(user: GithubUser): RequestBuilder<Drawable>
}