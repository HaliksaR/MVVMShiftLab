package ru.shiftlab.mvvmshiftlab.profile.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ProfileEntity
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ProfileHierarchyEntity
import ru.shiftlab.mvvmshiftlab.profile.domain.Profile
import ru.shiftlab.mvvmshiftlab.profile.domain.ProfileContainer

/**
 * Data Transfer Object - отвечают за синтаксический анализ ответов с сервера
 * или форматирование объектов для отправки на сервер. Необходимо преобразовать их в доменные объекты, прежде чем
 * использующий их.
 *
 * @see ru.shiftlab.mvvmshiftlab.profile.domain.Profile
 */


@JsonClass(generateAdapter = true)
data class ProfileNetwork(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "surname") val surname: String,
    @Json(name = "patronymic") val patronymic: String,
    @Json(name = "photo") val photo: String,
    @Json(name = "date_birth") val date_birth: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "city") val city: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "email") val email: String,
    @Json(name = "active_vacancies") val active_vacancies: List<ActiveVacancyNetwork>,
    @Json(name = "active_events") val active_events: List<ActiveEventNetwork>,
    @Json(name = "subscribe_events_tags") val subscribe_events_tags: List<String>,
    @Json(name = "subscribe_vacancies_tags") val subscribe_vacancies_tags: List<String>,
    @Json(name = "notifications") val notification_channels: NotificationsNetwork
)

fun asDomainModel(profiles: List<ProfileNetwork>): List<ProfileContainer> {
    return profiles.map {
        ProfileContainer(
            profile = Profile(
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
            ),
            active_vacancies = it.active_vacancies.asDomainModel(),
            active_events = it.active_events.asDomainModel(),
            notification_channels = it.notification_channels.asDomainModel()
        )
    }
}


fun asDatabaseModel(profiles: List<ProfileNetwork>): List<ProfileHierarchyEntity> {
    return profiles.map {
        ProfileHierarchyEntity(
            profile = ProfileEntity(
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
            ),
            active_vacancies = it.active_vacancies.asDatabaseModel(),
            active_events = it.active_events.asDatabaseModel(),
            notifications_channels = it.notification_channels.asDatabaseModel()
        )
    }
}

fun asDatabaseModelOnlyProfile(profile: ProfileNetwork): ProfileEntity {
    return ProfileEntity(
        id = profile.id,
        name = profile.name,
        surname = profile.surname,
        patronymic = profile.patronymic,
        photo = profile.photo,
        date_birth = profile.date_birth,
        gender = profile.gender,
        city = profile.city,
        phone = profile.phone,
        email = profile.email,
        subscribe_events_tags = profile.subscribe_events_tags,
        subscribe_vacancies_tags = profile.subscribe_vacancies_tags
    )
}

//??
fun asDatabaseModel(profile: ProfileNetwork): ProfileHierarchyEntity {
    return ProfileHierarchyEntity(
        profile = ProfileEntity(
            id = profile.id,
            name = profile.name,
            surname = profile.surname,
            patronymic = profile.patronymic,
            photo = profile.photo,
            date_birth = profile.date_birth,
            gender = profile.gender,
            city = profile.city,
            phone = profile.phone,
            email = profile.email,
            subscribe_events_tags = profile.subscribe_events_tags,
            subscribe_vacancies_tags = profile.subscribe_vacancies_tags
        ),
        active_vacancies = profile.active_vacancies.asDatabaseModel(),
        active_events = profile.active_events.asDatabaseModel(),
        notifications_channels = profile.notification_channels.asDatabaseModel()
    )

}
