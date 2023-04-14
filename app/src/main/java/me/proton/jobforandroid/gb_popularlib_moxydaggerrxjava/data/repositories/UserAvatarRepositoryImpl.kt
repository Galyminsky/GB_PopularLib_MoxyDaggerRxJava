package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.repositories

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.rxjava3.schedulers.Schedulers
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.entity.GithubUser
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.FolderHelper
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.NetworkStatus
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject


class UserAvatarRepositoryImpl @Inject constructor(
    private val context: Context,
    private val networkStatus: NetworkStatus,
) : UserAvatarRepository {

    override fun imageBuilder(user: GithubUser): RequestBuilder<Drawable> {
        val fileName = "avatar_${user.id}"
        val avatarFile = File(FolderHelper(context).imageFolder(), fileName)

        return networkStatus
            .isOnlineSingle()
            .subscribeOn(Schedulers.io())
            .map { internetEnable ->
                if (internetEnable) {
                    Glide.with(context)
                        .load(user.avatarUrl)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean,
                            ) = false

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean,
                            ): Boolean {
                                Log.d("My", "onResourceReady")
                                resource?.let {
                                    Log.d("My", "resource not null")
                                    FileOutputStream(avatarFile).use { fos ->
                                        it.toBitmap().compress(Bitmap.CompressFormat.JPEG, 80, fos)
                                        Log.d("My", "save resource in $fileName")
                                    }
                                }
                                return false
                            }
                        })
                } else {
                    Glide.with(context)
                        .load(avatarFile)
                }
            }.blockingGet()
    }
}