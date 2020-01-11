package ru.shiftlab.mvvmshiftlab.profile.domain

data class NotificationChannels(
    val telegram: Boolean,
    val email: Boolean,
    val mobile_notifications: Boolean
)