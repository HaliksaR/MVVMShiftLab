package ru.shiftlab.mvvmshiftlab.vacancy.network

import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyEntity
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy

data class VacancyNetworkEntity(
    var id: Int,
    val title: String,
    val type: String,
    val salary: Int
)


fun asDatabaseModel(vacancyEntities: List<VacancyNetworkEntity>): List<VacancyEntity> {
    return vacancyEntities.map {
        VacancyEntity(
            id = it.id,
            title = it.title,
            type = it.type,
            salary = it.salary
        )
    }
}

//???
fun asDatabaseModel(vacancyEntity: VacancyNetworkEntity): VacancyEntity {
    return VacancyEntity(
        id = vacancyEntity.id,
        title = vacancyEntity.title,
        type = vacancyEntity.type,
        salary = vacancyEntity.salary
    )

}

fun VacancyNetworkEntity.asDomainModel(): Vacancy {
    return Vacancy(
        id = id,
        title = title,
        type = type,
        salary = salary
    )

}

