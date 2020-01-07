package ru.shiftlab.mvvmshiftlab.profile.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.shiftlab.mvvmshiftlab.profile.database.ProfileDatabase
import ru.shiftlab.mvvmshiftlab.profile.database.asDomainModel
import ru.shiftlab.mvvmshiftlab.profile.domain.Profile
import ru.shiftlab.mvvmshiftlab.profile.network.ProfileApi
import ru.shiftlab.mvvmshiftlab.profile.network.asDatabaseModel

class ProfileRepository(private val database: ProfileDatabase) {

    val profiles: LiveData<List<Profile>> = Transformations.map(database.profileDao.getAll()){
        it.asDomainModel()
    }

    fun getProfile(id: Int) = Transformations.map(database.profileDao.getProfile(id)){
        Log.d("getProfile", "$it")
        it?.asDomainModel()
    }


    suspend fun refreshProfiles(){
        withContext(Dispatchers.IO){
            Log.d("ProfileRepository", "refresh PROFILES is called")
            val profiles = ProfileApi.retrofitService.getProfileAsync().await()
            database.profileDao.insertAll(asDatabaseModel(profiles))
        }
    }

    suspend fun refreshProfile(id: Int) {
        withContext(Dispatchers.IO){
            Log.d("ProfileRepository", "refresh PROFILE is called")
            val profile = ProfileApi.retrofitService.getProfileById(id).await()
            database.profileDao.insertProfile(asDatabaseModel(profile))
        }
    }
}