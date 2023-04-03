package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava

import android.app.Application
import androidx.fragment.app.Fragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.OurUsersRepoImpl
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { OurUsersRepoImpl() }
}

val Fragment.app: App get() = requireContext().applicationContext as App
