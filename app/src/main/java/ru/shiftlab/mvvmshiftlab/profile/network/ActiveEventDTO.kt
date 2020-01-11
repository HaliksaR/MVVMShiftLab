package ru.shiftlab.mvvmshiftlab.profile.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ActiveEventEntity
import ru.shiftlab.mvvmshiftlab.profile.domain.ActiveEvent

@JsonClass(generateAdapter = true)
data class ActiveEventNetwork(
    @Json(name = "id") val id: Int,
    @Json(name = "status") val status: Int
)


fun List<ActiveEventNetwork>.asDomainModel(): List<ActiveEvent> {
    return map {
        ActiveEvent(
            id = it.id,
            status = it.status
        )
    }
}

fun List<ActiveEventNetwork>.asDatabaseModel(): List<ActiveEventEntity> {
    return map {
        ActiveEventEntity(
            id = it.id,
            status = it.status
        )
    }
}