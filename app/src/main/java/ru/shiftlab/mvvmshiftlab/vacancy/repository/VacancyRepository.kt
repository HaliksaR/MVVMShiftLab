package ru.shiftlab.mvvmshiftlab.vacancy.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDatabase
import ru.shiftlab.mvvmshiftlab.vacancy.database.asDomainModel
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy
import ru.shiftlab.mvvmshiftlab.vacancy.network.VacancyApi
import ru.shiftlab.mvvmshiftlab.vacancy.network.asDatabaseModel
import ru.shiftlab.mvvmshiftlab.vacancy.network.asDomainModel

class VacancyRepository(private val database: VacancyDatabase) {

    val vacancies: LiveData<List<Vacancy>> = Transformations.map(database.vacancyDao.getAll()) {
        it.asDomainModel()
    }

    var vacancy = MutableLiveData<Vacancy>()

    suspend fun refreshVacancies(){
        withContext(Dispatchers.IO){
            Log.d("ProfileRepository", "refresh VACANCIES is called")
            val vacancies = VacancyApi.retrofitService.getVacanciesAsync().await()
            database.vacancyDao.insertAll(asDatabaseModel(vacancies))
        }
    }

    suspend fun getVacancyById(id: Int) {
        withContext(Dispatchers.Main){
            Log.d("ProfileRepository", "getVacancyById($id)")
            val vacancyNetwork = VacancyApi.retrofitService.getVacancyById(id).await()
            vacancy.value = vacancyNetwork.asDomainModel()
            Log.d("ProfileRepository", "${vacancy}")

        }
    }

}