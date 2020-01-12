package ru.shiftlab.mvvmshiftlab.profile.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.shiftlab.mvvmshiftlab.profile.database.ProfileDatabase
import ru.shiftlab.mvvmshiftlab.profile.repository.ProfileRepository

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val ID_PROFILE = 1

    private val profileRepository = ProfileRepository(ProfileDatabase.getInstance(application))

    val profile = profileRepository.profile
    val notificationChannels = profileRepository.notificationChannels
    val activeEvents = profileRepository.activeEvents
    val activeVacanies = profileRepository.activeVacanies

    //private val _response = MutableLiveData<String>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        refreshProfileDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        coroutineScope.launch {
            profileRepository.refreshProfile()
            try {
                profileRepository.refreshProfile()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (e: Exception) {
                Log.d("refreshData", "${e.message}")
                if (profile.value == null)
                    _eventNetworkError.value = true
            }
        }
    }

    private fun refreshProfileDataFromRepository() {
        coroutineScope.launch {
            profileRepository.refreshProfile()
            try {
                Log.d("refreshProfileData", "refresh PROFILE")

                profileRepository.refreshProfile()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                Log.d("refreshProfileData", "${e.message}")
                // Проверка
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    /*val response: LiveData<String>
        get() = _response



    init {
        getProfile()
    }*/


/*    private fun getProfile() {

        coroutineScope.launch {
            val getProfileDeferred = network.getProfileAsync()
            try {
                val listResponse = getProfileDeferred.await()
                _response.value = "Success: ${listResponse.size} profiles"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }

    }*/

    /*private fun getProfileById(id: Int) {
        coroutineScope.launch {
            val getProfileDeferred = network.getProfileById(id)
            try {
                val response = getProfileDeferred.await()
                _response.value = "Success: ${response.value}"
            } catch (e: java.lang.Exception){
                _response.value = "Failure: ${e.message}"
            }
        }
    }*/

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
