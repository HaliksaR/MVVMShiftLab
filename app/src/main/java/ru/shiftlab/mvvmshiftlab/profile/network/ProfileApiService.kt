package ru.shiftlab.mvvmshiftlab.profile.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

/**
 * Retrofit service для взаимодействия с сетью для [ProfileNetwork]
 */
interface ProfileApiService {
    @GET("profile")
    fun getProfileAsync(): Deferred<ProfileNetwork>
}