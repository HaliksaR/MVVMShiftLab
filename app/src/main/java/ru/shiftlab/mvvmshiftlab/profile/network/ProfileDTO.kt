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
data class ProfileNetworkEntity(
    val id: Int,
    val name: String,
    val status: String,
    val specialty: String
)


fun asDomainModel(profileEntities: List<ProfileNetworkEntity>): List<Profile> {
    return profileEntities.map {
        Profile(
            id = it.id,
            name = it.name,
            status = it.status,
            specialty = it.specialty
        )
    }
}


fun asDatabaseModel(profileEntities: List<ProfileNetworkEntity>): List<ProfileEntity> {
    return profileEntities.map {
        ProfileEntity(
            id = it.id,
            name = it.name,
            status = it.status,
            specialty = it.specialty
        )
    }
}

//??
fun asDatabaseModel(profileEntity: ProfileNetworkEntity): ProfileEntity {
    return ProfileEntity(
        id = profileEntity.id,
        name = profileEntity.name,
        status = profileEntity.status,
        specialty = profileEntity.specialty
    )

}
