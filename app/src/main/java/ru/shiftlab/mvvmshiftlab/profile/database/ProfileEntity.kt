package ru.shiftlab.mvvmshiftlab.profile.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.shiftlab.mvvmshiftlab.profile.domain.Profile

/**
 *
 * Класс который представляет сущность базы данных для профиля
 *
 * @param[id] индификатор профиля
 * @param[name] имя пользователя
 * @param[status] статус пользователя (лаборант, куратор и т.п.)
 * @param[specialty] специальность пользователя (Android разработчик и т.п.)
 *
 *
 */

@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "specialty")
    val specialty: String
)

/**
 * @receiver[asDomainModel] преобразует ProfileEntity в Profile
 */
fun List<ProfileEntity>.asDomainModel(): List<Profile> {
    return map {
        Profile(
            id = it.id,
            name = it.name,
            status = it.status,
            specialty = it.specialty
        )
    }
}

fun ProfileEntity.asDomainModel(): Profile {
    return Profile(id, name, status, specialty)
}