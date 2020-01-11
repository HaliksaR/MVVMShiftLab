package ru.shiftlab.mvvmshiftlab.profile.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.shiftlab.mvvmshiftlab.profile.database.ProfileDatabase
import ru.shiftlab.mvvmshiftlab.profile.database.entities.asDomainModel
import ru.shiftlab.mvvmshiftlab.profile.domain.Profile
import ru.shiftlab.mvvmshiftlab.profile.network.ProfileApi
import ru.shiftlab.mvvmshiftlab.profile.network.asDatabaseModelOnlyProfile

class ProfileRepository(private val database: ProfileDatabase) {

    val profile: LiveData<Profile> = Transformations.map(database.profileDao().getProfile()) {
        it.asDomainModel()
    }

    suspend fun refreshProfile() {
        withContext(Dispatchers.IO) {
            Log.d("ProfileRepository", "refresh PROFILES is called")
            val profiles = ProfileApi.retrofitService.getProfileAsync().await()
            database.profileDao().insertProfile(asDatabaseModelOnlyProfile(profiles))
        }
    }
}
