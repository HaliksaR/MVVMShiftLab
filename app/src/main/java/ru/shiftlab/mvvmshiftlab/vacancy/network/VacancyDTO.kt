package ru.shiftlab.mvvmshiftlab.vacancy.network

import com.squareup.moshi.Json
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyEntity
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy

data class VacancyNetwork(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "url") val url: String,
    @Json(name = "city") val city: String,
    @Json(name = "salary") val salary: String,
    @Json(name = "work_experience") val work_experience: String,
    @Json(name = "employment") val employment: String,
    @Json(name = "date_create") val date_create: String,
    @Json(name = "description") val description: String,
    @Json(name = "open") val open: Boolean,
    @Json(name = "tags") val tags: List<String>
)


fun asDatabaseModel(vacancies: List<VacancyNetwork>): List<VacancyEntity> {
    return vacancies.map {
        VacancyEntity(
            id = it.id,
            title = it.title,
            url = it.url,
            city = it.city,
            salary = it.salary,
            work_experience = it.work_experience,
            employment = it.employment,
            date_create = it.date_create,
            description = it.description,
            open = it.open,
            tags = it.tags
        )
    }
}

//???
fun asDatabaseModel(vacancy: VacancyNetwork): VacancyEntity {
    return VacancyEntity(
        id = vacancy.id,
        title = vacancy.title,
        url = vacancy.url,
        city = vacancy.city,
        salary = vacancy.salary,
        work_experience = vacancy.work_experience,
        employment = vacancy.employment,
        date_create = vacancy.date_create,
        description = vacancy.description,
        open = vacancy.open,
        tags = vacancy.tags
    )

}

fun VacancyNetwork.asDomainModel(): Vacancy {
    return Vacancy(
        id = id,
        title = title,
        url = url,
        city = city,
        salary = salary,
        work_experience = work_experience,
        employment = employment,
        date_create = date_create,
        description = description,
        open = open,
        tags = tags
    )

}

