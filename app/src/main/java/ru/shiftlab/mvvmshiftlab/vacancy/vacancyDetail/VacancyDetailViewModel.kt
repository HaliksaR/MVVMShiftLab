package ru.shiftlab.mvvmshiftlab.vacancy.vacancyDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDao
import ru.shiftlab.mvvmshiftlab.vacancy.database.asDomainModel

class VacancyDetailViewModel(private val vacancyId: Int = 1,
                             dataSource: VacancyDao
) : ViewModel() {

    val database = dataSource

    private val viewModelJob = Job()

    private val vacancy  = dataSource.getVacancyById(vacancyId)

    val stringSalary = Transformations.map(vacancy) {
        it.asDomainModel().salary.toString() + " руб"
    }


    fun getVacancy() = Transformations.map(vacancy) {
        it.asDomainModel()
    }

    private val _navigateToVacancy = MutableLiveData<Boolean?>()
    val navigateToVacancy : LiveData<Boolean?>
    get() = _navigateToVacancy

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToVacancy.value = null
    }

    fun onClose() {
        _navigateToVacancy.value = true
    }

}
