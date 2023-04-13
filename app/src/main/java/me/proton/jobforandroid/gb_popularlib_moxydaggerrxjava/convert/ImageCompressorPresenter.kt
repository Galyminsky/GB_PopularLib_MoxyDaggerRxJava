package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert

import android.content.Intent
import android.graphics.Bitmap
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.kotlin.plusAssign
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.BasePresenter
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules.AppSchedulers

class ImageCompressorPresenter constructor(
    private val converter: ImageConverter,
    private val appSchedulers: AppSchedulers,
    router: Router,
) : BasePresenter<ImageCompressorView>(router) {

    var bitmap: Bitmap? = null
        set(value) {
            field = value
            field?.let {
                val properties = mutableListOf<Pair<String, String>>()
                properties.add(("Ширина" to it.width.toString()))
                properties.add(("Высота" to it.height.toString()))
                val propertiesText = propertiesToText(properties)
                viewState.showImageProperties(propertiesText)
                viewState.endSelectImage()
            }
        }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {

    }

    fun selectImage() {
        viewState.startSelectImage()
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        viewState.openIntent(intent)
    }

    fun saved(compressor: Int, size: Int) {
        bitmap?.let { bmp ->
            disposables +=
                converter
                    .convert(
                        bitmap = bmp,
                        imageCompressorPercent = compressor,
                        imageSizePercent = size
                    )
                    .observeOn(appSchedulers.main())
                    .subscribeOn(appSchedulers.background())
                    .subscribe(
                        { uri ->
                            viewState.processSaveImage(false)
                            viewState.showSavedImage(uri)
                        },
                        { t ->
                            viewState.processSaveImage(false)
                            viewState.showException(t)
                        }
                    )
        }
    }

    fun cancel() {
        disposables.clear()
        viewState.processSaveImage(false)
    }

    private fun propertiesToText(properties: List<Pair<String, String>>): String {
        return properties
            .map { property -> "${property.first} = ${property.second}" }
            .joinToString(separator = ", ") { value -> value }
    }
}