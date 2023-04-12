package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.scheduler

import io.reactivex.Scheduler

interface Schedulers {

    fun background(): Scheduler
    fun main(): Scheduler

}