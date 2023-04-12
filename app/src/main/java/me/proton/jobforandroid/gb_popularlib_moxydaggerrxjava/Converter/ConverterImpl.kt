package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.Converter

import android.content.Context
import android.net.Uri
import io.reactivex.rxjava3.core.Single

class ConverterImpl(private val context: Context) : Converter {

    override fun convert(uri: Uri): Single<Uri> =
        ConverterSingle(context, uri)

}