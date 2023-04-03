package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain

interface UsersRepo {
    fun getUsers(
        onSuccess: (List<UserEntity>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}