package ru.shiftlab.mvvmshiftlab.profile.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit service для взаимодействия с сетью для [ProfileNetwork]
 */
interface ProfileApiService {

    @GET("profile")
    fun getProfileAsync(): Deferred<List<ProfileNetwork>>

    @GET("profile/{id}")
    fun getProfileById(@Path("id") id: Int): Deferred<ProfileNetwork>
}