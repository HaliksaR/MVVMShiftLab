package ru.shiftlab.mvvmshiftlab.profile.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.shiftlab.mvvmshiftlab.profile.domain.Profile


@Entity(tableName = "profile_table")
data class ProfileEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surname") val surname: String,
    @ColumnInfo(name = "patronymic") val patronymic: String,
    @ColumnInfo(name = "photo") val photo: String,
    @ColumnInfo(name = "date_birth") val date_birth: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "subscribe_events_tags") val subscribe_events_tags: List<String>,
    @ColumnInfo(name = "subscribe_vacancies_tags") val subscribe_vacancies_tags: List<String>
)

/**
 * @receiver[asDomainModel] преобразует ProfileEntity в Profile
 */
fun List<ProfileEntity>.asDomainModel(): List<Profile> {
    return map {
        Profile(
            id = it.id,
            name = it.name,
            surname = it.surname,
            patronymic = it.patronymic,
            photo = it.photo,
            date_birth = it.date_birth,
            gender = it.gender,
            city = it.city,
            phone = it.phone,
            email = it.email,
            subscribe_events_tags = it.subscribe_events_tags,
            subscribe_vacancies_tags = it.subscribe_vacancies_tags
        )
    }
}

fun ProfileEntity.asDomainModel(): Profile {
    return Profile(
        id = id,
        name = name,
        surname = surname,
        patronymic = patronymic,
        photo = photo,
        date_birth = date_birth,
        gender = gender,
        city = city,
        phone = phone,
        email = email,
        subscribe_events_tags = subscribe_events_tags,
        subscribe_vacancies_tags = subscribe_vacancies_tags
    )
}