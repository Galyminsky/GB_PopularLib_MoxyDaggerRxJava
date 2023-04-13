package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import com.google.android.material.snackbar.Snackbar
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.baselogic.BackButtonListener
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.databinding.ImageCompressorFragmentBinding
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.extensions.showToast
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.di.BaseDaggerFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules.AppSchedulers
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ImageCompressorFragment : BaseDaggerFragment(), ImageCompressorView, BackButtonListener {

    companion object {
        fun newInstance(): Fragment = ImageCompressorFragment()
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var appSchedulers: AppSchedulers

    @Inject
    lateinit var imageConverter: ImageConverter

    private val binding: ImageCompressorFragmentBinding by viewBinding(createMethod = CreateMethod.INFLATE)
    private val presenter by moxyPresenter {
        ImageCompressorPresenter(
            imageConverter,
            appSchedulers,
            router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        initListeners()
        binding.imageCompressorValueId.value = 100f
        binding.imageSizeValueId.value = 100f
        return binding.root
    }

    override fun openIntent(intent: Intent) {
        resultLauncher.launch(intent)
    }

    override fun showImageProperties(properties: String) {
        binding.imagePropertiesId.text = properties
    }

    override fun startSelectImage() {
        binding.imageId.visibility = View.GONE
        binding.compressDataId.visibility = View.GONE
        binding.buttonSaveId.visibility = View.GONE
        binding.imageCompressorValueId.value = 100f
        binding.imageSizeValueId.value = 100f
    }

    override fun endSelectImage() {
        binding.imageId.visibility = View.VISIBLE
        binding.compressDataId.visibility = View.VISIBLE
        binding.buttonSaveId.visibility = View.VISIBLE
    }

    override fun processSaveImage(asStarted: Boolean) {
        binding.buttonOpenId.isEnabled = !asStarted
        binding.imageId.isEnabled = !asStarted
        binding.imageCompressorValueId.isEnabled = !asStarted
        binding.imageSizeValueId.isEnabled = !asStarted
        binding.buttonSaveId.isEnabled = !asStarted
        binding.progressBar.visibility = if (asStarted) {
            View.VISIBLE
        } else {
            View.GONE
        }
        binding.buttonCancelId.visibility = if (asStarted) {
            View.VISIBLE
        } else {
            View.GONE
        }

        if (asStarted) {
            binding.imageSavedId.setImageURI(null)
        }
    }

    override fun showSavedImage(uri: Uri) {
        binding.imageSavedId.setImageURI(uri)
    }

    fun showException(throwable: Throwable) {
        context?.showToast(throwable.message)
    }

    override fun backPressed() = presenter.backPressed()

    private fun initListeners() {
        binding.buttonOpenId.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                presenter.selectImage()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.message_from_permission),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(R.string.permission) {
                            permissionCallResult.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        }
                        .show()
                } else {
                    permissionCallResult.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }
        }

        binding.buttonSaveId.setOnClickListener {
            with(binding) {
                presenter.saved(
                    imageCompressorValueId.value.toInt(),
                    imageSizeValueId.value.toInt()
                )
            }
        }

        binding.buttonCancelId.setOnClickListener { presenter.cancel() }

        binding.imageCompressorValueId.addOnChangeListener { _, value, _ ->
            val title = String.format(getString(R.string.image_compressor_title), value.toInt())
            binding.imageCompressorTitleId.text = title
        }

        binding.imageSizeValueId.addOnChangeListener { _, value, _ ->
            val title = String.format(getString(R.string.image_size_title), value.toInt())
            binding.imageSizeTitleId.text = title
        }
    }

    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                data?.data?.let {
                    val bitmap =
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                            ImageDecoder.decodeBitmap(
                                ImageDecoder.createSource(
                                    requireActivity().contentResolver,
                                    it
                                )
                            )
                        } else {
                            MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, it)
                        }
                    presenter.bitmap = bitmap

                    binding.imageId.setImageURI(it)
                }
            }
        }

    private val permissionCallResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            context?.showToast(
                getString(
                    if (it) {
                        R.string.no_permission_ok
                    } else {
                        R.string.no_permission
                    }
                )
            )
        }
}