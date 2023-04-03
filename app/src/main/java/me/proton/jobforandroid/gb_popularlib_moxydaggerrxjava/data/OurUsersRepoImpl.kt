package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data

import android.os.Handler
import android.os.Looper
import androidx.core.os.postDelayed
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UserEntity
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UsersRepo


class OurUsersRepoImpl : UsersRepo {

    private val data: List<UserEntity> = listOf(
        UserEntity("Alex", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntity("Max", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntity("Petr", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntity("Ivan", 4, "https://avatars.githubusercontent.com/u/4?v=4"),
        UserEntity("Yuriy", 5, "https://avatars.githubusercontent.com/u/5?v=4"),
        UserEntity("Maksim", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        UserEntity("Bruno", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        UserEntity("Ivey", 8, "https://avatars.githubusercontent.com/u/17?v=4"),
        UserEntity("Sergey", 9, "https://avatars.githubusercontent.com/u/18?v=4"),
        UserEntity("Andrew", 10, "https://avatars.githubusercontent.com/u/19?v=4"),
        UserEntity("Alexandr", 11, "https://avatars.githubusercontent.com/u/20?v=4"),
    )

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        Handler(Looper.getMainLooper()).postDelayed(2000) {
            if ((0..3).random() == 1) {
                onError?.let { it(Throwable("Ошибочка")) }
            }
            onSuccess(data)
        }
    }
}