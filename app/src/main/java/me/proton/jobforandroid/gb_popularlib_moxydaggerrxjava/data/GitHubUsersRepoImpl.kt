package me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data


import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.data.retrofit.UsersGitHubAPI
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UserEntity
import me.proton.jobforandroid.gb_popularlib_moxydaggerrxjava.domain.UsersRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubUsersRepoImpl : UsersRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(UsersGitHubAPI::class.java)

    override fun getUsers(onSuccess: (List<UserEntity>) -> Unit, onError: ((Throwable) -> Unit)?) {
        api.getListUsers().enqueue(object : Callback<List<UserEntity>> {
            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                val body = response.body()
                if (response.isSuccessful && !body.isNullOrEmpty()) {
                    onSuccess(body)
                } else {
                    onError?.invoke(IllegalStateException("Данных нет или ошибка!"))
                }
            }

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                onError?.invoke(t)
            }
        })

    }
}