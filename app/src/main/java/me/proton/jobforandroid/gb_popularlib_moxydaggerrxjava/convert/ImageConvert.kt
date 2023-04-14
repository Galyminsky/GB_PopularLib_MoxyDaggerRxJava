package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert

import android.annotation.TargetApi
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.net.toUri
import io.reactivex.rxjava3.android.MainThreadDisposable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import java.io.File
import java.io.FileOutputStream
import java.util.*
import java.util.concurrent.Executors

class ImageConvert(
    private val context: Context,
    private val bitmap: Bitmap,
    private val imageCompressorPercent: Int,
    private val imageSizePercent: Int,
) : Single<Uri>() {
    override fun subscribeActual(observer: SingleObserver<in Uri>) {
        val listener =
            ConvertListener(context, bitmap, imageCompressorPercent, imageSizePercent, observer)
        observer.onSubscribe(listener)
        listener.start()
    }

    class ConvertListener(
        private val context: Context,
        private val bitmap: Bitmap,
        private val imageCompressorPercent: Int,
        private val imageSizePercent: Int,
        private val observer: SingleObserver<in Uri>,
    ) : MainThreadDisposable(), Runnable {

        private val convertTask by lazy {
            Executors
                .newSingleThreadExecutor()
                .submit(this) //вызывается run
        }

        override fun onDispose() {
            convertTask
                ?.takeIf { !isDisposed }
                ?.takeIf { task -> !task.isDone }
                ?.takeIf { task -> !task.isCancelled }
                ?.cancel(true)
        }

        override fun run() {
            try {
                var newBitmap = bitmap

                if (imageSizePercent < 100) {
                    val oldWidth = bitmap.width
                    val oldHeight = bitmap.height
                    val newWidth: Int = oldWidth * imageSizePercent / 100
                    val newHeight: Int = oldHeight * imageSizePercent / 100
                    newBitmap = Bitmap.createScaledBitmap(newBitmap, newWidth, newHeight, false)
                }

                val name = "${System.currentTimeMillis()}.jpg"
                val savedUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    saveImageForAndroid10(newBitmap, imageCompressorPercent, name)
                } else {
                    saveImage(newBitmap, imageCompressorPercent, name)
                }

                putResult(savedUri)
            } catch (e: Exception) {
                observer.onError(e)
            }
        }

        private fun putResult(savedUri: Uri?) {
            savedUri?.let { uri ->
                convertTask
                    ?.takeIf { !isDisposed }
                    ?.takeIf { task -> !task.isDone }
                    ?.takeIf { task -> !task.isCancelled }
                    ?.let { observer.onSuccess(uri) }
            }
        }

        fun start() {
            convertTask
        }

        @TargetApi(Build.VERSION_CODES.Q)
        private fun saveImageForAndroid10(
            bitmap: Bitmap,
            imageCompressorPercent: Int,
            name: String,
        ): Uri? {
            val resolver: ContentResolver = context.contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            contentValues.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_DOWNLOADS
            )
            val imageUri: Uri? = resolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            Objects.requireNonNull(imageUri)?.let {
                resolver.openOutputStream(it)
            }.use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, imageCompressorPercent, it)
            }
            return imageUri
        }

        private fun saveImage(bitmap: Bitmap, imageCompressorPercent: Int, name: String): Uri? {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val image = File(imagesDir, name)
            FileOutputStream(image).use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, imageCompressorPercent, it)
            }

            return image.toUri()
        }
    }
}