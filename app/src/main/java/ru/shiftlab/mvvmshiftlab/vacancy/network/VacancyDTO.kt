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
    @Json(name = "date") val date: String,
    @Json(name = "description") val description: String,
    @Json(name = "status_vacancy") val status_vacancy: Boolean,
    @Json(name = "submit") val submit: Boolean,
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
            date = it.date,
            description = it.description,
            status_vacancy = it.status_vacancy,
            submit = it.submit,
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
        date = vacancy.date,
        description = vacancy.description,
        status_vacancy = vacancy.status_vacancy,
        submit = vacancy.submit,
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
        date = date,
        description = description,
        status_vacancy = status_vacancy,
        submit = submit,
        tags = tags
    )

}

