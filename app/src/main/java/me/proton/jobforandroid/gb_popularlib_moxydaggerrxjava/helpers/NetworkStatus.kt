package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.helpers

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetworkStatus {

    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>

}