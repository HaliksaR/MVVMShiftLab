package ru.shiftlab.mvvmshiftlab.profile.database.entities

import androidx.room.*
import ru.shiftlab.mvvmshiftlab.profile.domain.ActiveEvent

@Entity(
    tableName = "active_events",
    foreignKeys = [
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profile_id"]
        )
    ],
    indices = [Index("profile_id")]
)
data class ActiveEventEntity(
    @ColumnInfo(name = "event_id") val id: Int,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "profile_id") val profileId: Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var activeEventId: Int = 0
}

fun List<ActiveEventEntity>.asDomainModel(): List<ActiveEvent> {
    return map {
        ActiveEvent(
            id = it.id,
            status = it.status
        )
    }
}

fun ActiveEventEntity.asDomainModel(): ActiveEvent {
    return ActiveEvent(
        id = id,
        status = status
    )
}