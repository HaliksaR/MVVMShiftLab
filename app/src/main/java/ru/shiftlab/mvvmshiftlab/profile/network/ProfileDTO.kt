package ru.shiftlab.mvvmshiftlab.profile.network

import com.squareup.moshi.JsonClass
import ru.shiftlab.mvvmshiftlab.profile.database.ProfileEntity
import ru.shiftlab.mvvmshiftlab.profile.domain.Profile

/**
 * Data Transfer Object - отвечают за синтаксический анализ ответов с сервера
 * или форматирование объектов для отправки на сервер. Необходимо преобразовать их в доменные объекты, прежде чем
 * использующий их.
 *
 * @see ru.shiftlab.mvvmshiftlab.profile.domain.Profile
 */


/**
 * Класс который представляет сущность профиля из сервиса
 * @param[id] индификатор профиля
 * @param[name] имя пользователя
 * @param[status] статус пользователя (лаборант, куратор и т.п.)
 * @param[specialty] специальность пользователя (Android разработчик и т.п.)
 *
 */
@JsonClass(generateAdapter = true)
data class ProfileNetwork(
    val id: Int,
    val name: String,
    val status: String,
    val specialty: String
)


fun asDomainModel(profiles: List<ProfileNetwork>): List<Profile> {
    return profiles.map {
        Profile(
            id = it.id,
            name = it.name,
            status = it.status,
            specialty = it.specialty
        )
    }
}


fun asDatabaseModel(profiles: List<ProfileNetwork>): List<ProfileEntity> {
    return profiles.map {
        ProfileEntity(
            id = it.id,
            name = it.name,
            status = it.status,
            specialty = it.specialty
        )
    }
}

//??
fun asDatabaseModel(profile: ProfileNetwork): ProfileEntity {
    return ProfileEntity(
        id = profile.id,
        name = profile.name,
        status = profile.status,
        specialty = profile.specialty
    )

}
