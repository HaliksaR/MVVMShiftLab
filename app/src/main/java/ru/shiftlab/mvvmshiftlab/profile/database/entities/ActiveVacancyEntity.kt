package ru.shiftlab.mvvmshiftlab.profile.database.entities

import androidx.room.*
import ru.shiftlab.mvvmshiftlab.profile.domain.ActiveVacancy

@Entity(
    tableName = "active_vacancies",
    foreignKeys = [
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profile_id"]
        )
    ],
    indices = [Index("profile_id")]
)
data class ActiveVacancyEntity(
    @ColumnInfo(name = "vacancy_id") val id: Int,
    @ColumnInfo(name = "status") val status: Int,
    @ColumnInfo(name = "profile_id") val profileId: Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var activeVacancyId: Int = 0
}

fun List<ActiveVacancyEntity>.asDomainModel(): List<ActiveVacancy> {
    return map {
        ActiveVacancy(
            id = it.id,
            status = it.status
        )
    }
}

fun ActiveVacancyEntity.asDomainModel(): ActiveVacancy {
    return ActiveVacancy(
        id = id,
        status = status
    )
}