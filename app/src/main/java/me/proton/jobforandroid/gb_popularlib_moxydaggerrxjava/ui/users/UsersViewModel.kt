package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.ui.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UserEntity
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UsersRepo

class UsersViewModel(private val usersRepo: UsersRepo) : ViewModel() {

    private val usersLiveData: MutableLiveData<List<UserEntity>> = MutableLiveData()
    private val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    private val progressLiveData: MutableLiveData<Boolean> = MutableLiveData()


    fun getUsersLivedata() = usersLiveData
    fun getErrorLivedata() = errorLiveData
    fun getProgressLivedata() = progressLiveData

    fun loadData() {
        progressLiveData.postValue(true)
        usersRepo.getUsers(onSuccess = {
            usersLiveData.postValue(it)
            progressLiveData.postValue(false)
        }, onError = {
            errorLiveData.postValue(it)
            progressLiveData.postValue(false)
        })
    }
}