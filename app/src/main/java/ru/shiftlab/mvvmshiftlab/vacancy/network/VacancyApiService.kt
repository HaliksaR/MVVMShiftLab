package ru.shiftlab.mvvmshiftlab.vacancy.network

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy

interface VacancyApiService {

    @GET("vacancy")
    fun getVacanciesAsync() : Deferred<List<VacancyNetwork>>

    @GET("vacancy/{id}")
    fun getVacancyById(@Path("id") id: Int) : Deferred<VacancyNetwork>
}