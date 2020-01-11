package ru.shiftlab.mvvmshiftlab.profile.database.queries

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.shiftlab.mvvmshiftlab.profile.database.entities.NotificationChannelsEntity

@Dao
interface NotificationChannelsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotificationChannels(notification_channel: NotificationChannelsEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNotificationChannels(notification_channel: NotificationChannelsEntity)

    @Delete
    suspend fun deleteNotificationChannels(notification_channel: NotificationChannelsEntity)

    @Query("SELECT * FROM notification_channels")
    fun getAll(): LiveData<List<NotificationChannelsEntity>>

}