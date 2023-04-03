package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    fun getCounter(index: Int) = counters[index]

    fun increaseCounter(index: Int): Int {
        counters[index]++
        return counters[index]
    }
}