package ru.shiftlab.mvvmshiftlab.profile.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.shiftlab.mvvmshiftlab.profile.database.entities.NotificationChannelsEntity
import ru.shiftlab.mvvmshiftlab.profile.domain.NotificationChannels

@JsonClass(generateAdapter = true)
data class NotificationsNetwork(
    @Json(name = "telegram") val telegram: Boolean,
    @Json(name = "email") val email: Boolean,
    @Json(name = "mobile_notifications") val mobile_notifications: Boolean
)


fun NotificationsNetwork.asDomainModel(): NotificationChannels {
    return NotificationChannels(
        telegram = telegram,
        email = email,
        mobile_notifications = mobile_notifications
    )
}

fun NotificationsNetwork.asDatabaseModel(): NotificationChannelsEntity {
    return NotificationChannelsEntity(
        telegram = telegram,
        email = email,
        mobile_notifications = mobile_notifications
    )
}
