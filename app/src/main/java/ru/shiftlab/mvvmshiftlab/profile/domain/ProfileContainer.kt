package ru.shiftlab.mvvmshiftlab.profile.domain

data class ProfileContainer(
    val profile: Profile,
    val notification_channels: NotificationChannels,
    val active_vacancies: List<ActiveVacancy>,
    val active_events: List<ActiveEvent>
)