package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.Scheduler

import io.reactivex.rxjava3.core.Scheduler

interface Schedulers {

    fun background(): Scheduler
    fun main(): Scheduler

}