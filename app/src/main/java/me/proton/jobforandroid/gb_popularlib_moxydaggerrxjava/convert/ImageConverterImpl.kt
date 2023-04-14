package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ImageConverterImpl @Inject constructor(private val context: Context) : ImageConverter {

    override fun convert(
        bitmap: Bitmap,
        imageCompressorPercent: Int,
        imageSizePercent: Int,
    ): Single<Uri> = ImageConvert(context, bitmap, imageCompressorPercent, imageSizePercent)
}