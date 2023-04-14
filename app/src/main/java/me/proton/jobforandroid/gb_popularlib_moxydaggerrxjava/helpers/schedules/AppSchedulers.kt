package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers.schedules

import io.reactivex.rxjava3.core.Scheduler

interface AppSchedulers {

    fun background(): Scheduler
    fun main(): Scheduler
}