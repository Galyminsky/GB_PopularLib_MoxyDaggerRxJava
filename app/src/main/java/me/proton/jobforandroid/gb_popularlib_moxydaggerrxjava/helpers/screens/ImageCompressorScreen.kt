package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.convert.ImageCompressorFragment

class ImageCompressorScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        ImageCompressorFragment.newInstance()
}