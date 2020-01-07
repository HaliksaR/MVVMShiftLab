package ru.shiftlab.mvvmshiftlab.vacancy.network

import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyEntity
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy

data class VacancyNetwork(
    var id: Int,
    val title: String,
    val type: String,
    val salary: Int)


fun asDatabaseModel(vacancies: List<VacancyNetwork>) : List<VacancyEntity>{
    return vacancies.map {
        VacancyEntity(
            id = it.id,
            title = it.title,
            type = it.type,
            salary = it.salary
        )
    }
}

//???
fun asDatabaseModel(vacancy: VacancyNetwork) : VacancyEntity{
    return VacancyEntity(
        id = vacancy.id,
        title = vacancy.title,
        type = vacancy.type,
        salary = vacancy.salary
    )

}

fun VacancyNetwork.asDomainModel() : Vacancy{
    return Vacancy(
        id = id,
        title = title,
        type = type,
        salary = salary
    )

}

