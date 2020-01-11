package ru.shiftlab.mvvmshiftlab.vacancy.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface VacancyApiService {

    @GET("vacancies")
    fun getVacanciesAsync(): Deferred<List<VacancyNetwork>>

    @GET("vacancies/{id}")
    fun getVacancyByIdAsync(@Path("id") id: Int): Deferred<VacancyNetwork>
}