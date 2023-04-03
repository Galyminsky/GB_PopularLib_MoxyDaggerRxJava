package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.cicerone

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.counters.CounterFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.profile.ProfileFragment
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.users.UsersFragment

class AndroidScreens : Screens {
    override fun counter() = FragmentScreen { CounterFragment.newInstance() }
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun profile(bundle: Bundle) = FragmentScreen { ProfileFragment.newInstance(bundle) }
}