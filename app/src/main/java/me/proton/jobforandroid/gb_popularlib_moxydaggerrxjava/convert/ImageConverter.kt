package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert

import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.rxjava3.core.Single

interface ImageConverter {

    fun convert(bitmap: Bitmap, imageCompressorPercent: Int, imageSizePercent: Int): Single<Uri>
}