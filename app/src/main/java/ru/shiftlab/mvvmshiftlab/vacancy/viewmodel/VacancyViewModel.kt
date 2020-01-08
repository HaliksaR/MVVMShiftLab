package ru.shiftlab.mvvmshiftlab.vacancy.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.*
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDao
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDatabase
import ru.shiftlab.mvvmshiftlab.vacancy.repository.VacancyRepository
import ru.shiftlab.mvvmshiftlab.vacancyFormat
import java.lang.Exception

class VacancyViewModel(
    val database: VacancyDao,
    application: Application
) : AndroidViewModel(application) {


    private val vacancyRepository = VacancyRepository(VacancyDatabase.getInstance(application))

    private var viewModelJob = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var vacancy: LiveData<Vacancy> = vacancyRepository.vacancy

    val vacancies = vacancyRepository.vacancies

    val vacanciesString = Transformations.map(vacancies) {
        vacancyFormat(it)
    }

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        uiScope.launch {
            vacancyRepository.refreshVacancies()
            try {
                vacancyRepository.refreshVacancies()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            } catch (e: Exception) {
                Log.d("refreshData", "${e.message}")
                if (vacancies.value!!.isEmpty())
                    _eventNetworkError.value = true

            }
        }
    }

    private fun getVacancyById(id: Int) {
        uiScope.launch {
            vacancyRepository.getVacancyById(id)
        }
    }


    fun onClear() {
        uiScope.launch {
            clear()
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val _navigationToVacancyDetail = MutableLiveData<Int>()
    val navigateToVacancyDetail
        get() = _navigationToVacancyDetail

    fun onVacancyDetailNavigated() {
        _navigationToVacancyDetail.value = null
    }

    fun onVacancyClicked(id: Int) {
        _navigationToVacancyDetail.value = id
    }

    fun doneNavigating() {
        _navigationToVacancyDetail.value = null
    }

}
