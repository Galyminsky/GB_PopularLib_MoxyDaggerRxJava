package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.Converter

import android.net.Uri
import io.reactivex.rxjava3.core.Single

interface Converter {

    fun convert(uri: Uri): Single<Uri>

}