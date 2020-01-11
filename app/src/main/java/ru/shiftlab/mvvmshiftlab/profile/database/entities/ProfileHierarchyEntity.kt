package ru.shiftlab.mvvmshiftlab.profile.database.entities

import androidx.room.Embedded
import androidx.room.Relation
import ru.shiftlab.mvvmshiftlab.profile.domain.ProfileContainer

// https://habr.com/ru/post/349280/

data class ProfileHierarchyEntity(
    @Embedded val profile: ProfileEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "profile_id"
    ) val notifications_channels: NotificationChannelsEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "profile_id"
    ) val active_vacancies: List<ActiveVacancyEntity> = emptyList(),

    @Relation(
        parentColumn = "id",
        entityColumn = "profile_id"
    ) val active_events: List<ActiveEventEntity> = emptyList()
)

fun List<ProfileHierarchyEntity>.asDomainModel(): List<ProfileContainer> {
    return map {
        ProfileContainer(
            profile = it.profile.asDomainModel(),
            notification_channels = it.notifications_channels.asDomainModel(),
            active_vacancies = it.active_vacancies.asDomainModel(),
            active_events = it.active_events.asDomainModel()
        )
    }
}

fun ProfileHierarchyEntity.asDomainModel(): ProfileContainer {
    return ProfileContainer(
        profile = profile.asDomainModel(),
        notification_channels = notifications_channels.asDomainModel(),
        active_vacancies = active_vacancies.asDomainModel(),
        active_events = active_events.asDomainModel()
    )
}