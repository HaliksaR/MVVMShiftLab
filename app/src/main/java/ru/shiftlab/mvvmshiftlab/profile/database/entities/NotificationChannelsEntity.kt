package ru.shiftlab.mvvmshiftlab.profile.database.entities

import androidx.room.*
import ru.shiftlab.mvvmshiftlab.profile.domain.NotificationChannels

@Entity(
    tableName = "notification_channels",
    foreignKeys = [
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profile_id"]
        )
    ],
    indices = [Index("profile_id")]
)
data class NotificationChannelsEntity(
    @ColumnInfo(name = "telegram") val telegram: Boolean,
    @ColumnInfo(name = "email") val email: Boolean,
    @ColumnInfo(name = "mobile_notifications") val mobile_notifications: Boolean,
    @ColumnInfo(name = "profile_id") val profileId: Int? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var notificationChannelsId: Int = 0
}

fun NotificationChannelsEntity.asDomainModel(): NotificationChannels {
    return NotificationChannels(
        telegram = telegram,
        email = telegram,
        mobile_notifications = telegram
    )
}