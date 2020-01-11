package ru.shiftlab.mvvmshiftlab.profile.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ActiveVacancyEntity
import ru.shiftlab.mvvmshiftlab.profile.domain.ActiveVacancy

@JsonClass(generateAdapter = true)
data class ActiveVacancyNetwork(
    @Json(name = "id") val id: Int,
    @Json(name = "status") val status: Int
)


fun List<ActiveVacancyNetwork>.asDomainModel(): List<ActiveVacancy> {
    return map {
        ActiveVacancy(
            id = it.id,
            status = it.status
        )
    }
}

fun List<ActiveVacancyNetwork>.asDatabaseModel(): List<ActiveVacancyEntity> {
    return map {
        ActiveVacancyEntity(
            id = it.id,
            status = it.status
        )
    }
}