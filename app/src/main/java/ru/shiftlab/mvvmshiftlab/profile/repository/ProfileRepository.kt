package ru.shiftlab.mvvmshiftlab.profile.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.shiftlab.mvvmshiftlab.profile.database.ProfileDatabase
import ru.shiftlab.mvvmshiftlab.profile.database.entities.asDomainModel
import ru.shiftlab.mvvmshiftlab.profile.domain.ActiveEvent
import ru.shiftlab.mvvmshiftlab.profile.domain.ActiveVacancy
import ru.shiftlab.mvvmshiftlab.profile.domain.NotificationChannels
import ru.shiftlab.mvvmshiftlab.profile.domain.Profile
import ru.shiftlab.mvvmshiftlab.profile.network.ProfileApi
import ru.shiftlab.mvvmshiftlab.profile.network.asDatabaseModel
import ru.shiftlab.mvvmshiftlab.profile.network.asDatabaseModelOnlyProfile

class ProfileRepository(private val database: ProfileDatabase) {

    val profile: LiveData<Profile> = Transformations.map(database.profileDao().getProfile()) {
        it.asDomainModel()
    }

    val activeEvents: LiveData<List<ActiveEvent>> =
        Transformations.map(database.activeEventDao().getAll()) {
            it.asDomainModel()
        }

    val activeVacanies: LiveData<List<ActiveVacancy>> =
        Transformations.map(database.activeVacancyDao().getAll()) {
            it.asDomainModel()
        }

    val notificationChannels: LiveData<NotificationChannels> =
        Transformations.map(database.notificationChannelsDao().getNotificationChannels()) {
            it.asDomainModel()
        }

    suspend fun refreshProfile() {
        withContext(Dispatchers.IO) {
            Log.d("ProfileRepository", "refresh PROFILES is called")
            val profile = ProfileApi.retrofitService.getProfileAsync().await()
            Log.d("ProfileRepository", profile.toString())
            database.run {
                profileDao().insertProfile(asDatabaseModelOnlyProfile(profile))
                activeEventDao().insertAll(profile.active_events.asDatabaseModel())
                activeVacancyDao().insertAll(profile.active_vacancies.asDatabaseModel())
                notificationChannelsDao().insertNotificationChannels(profile.notification_channels.asDatabaseModel())
            }
        }
    }
}
