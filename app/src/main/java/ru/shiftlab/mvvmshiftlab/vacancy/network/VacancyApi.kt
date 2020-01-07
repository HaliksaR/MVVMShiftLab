package ru.shiftlab.mvvmshiftlab.vacancy.network

import ru.shiftlab.mvvmshiftlab.network.RetrofitService

object VacancyApi {
    val retrofitService: VacancyApiService by lazy {
        RetrofitService.retrofit.create(
            VacancyApiService::class.java)
    }
}