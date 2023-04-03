package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.R
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment.newInstance())
                .commit()
        }
    }
}