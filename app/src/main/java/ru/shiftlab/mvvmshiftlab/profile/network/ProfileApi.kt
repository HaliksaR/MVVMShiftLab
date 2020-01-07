package ru.shiftlab.mvvmshiftlab.profile.network

import ru.shiftlab.mvvmshiftlab.network.RetrofitService

/**
 * Иницализация Retrofit Service для [ProfileApiService]
 */
object ProfileApi {
    val retrofitService : ProfileApiService by lazy {
        RetrofitService.retrofit.create(
            ProfileApiService::class.java)
    }
}