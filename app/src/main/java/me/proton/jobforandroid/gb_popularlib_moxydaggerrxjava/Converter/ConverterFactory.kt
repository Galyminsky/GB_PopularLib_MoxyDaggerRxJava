package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.Converter

import android.content.Context

object ConverterFactory {

    fun create(context: Context): Converter = ConverterImpl(context)

}