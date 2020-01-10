package ru.shiftlab.mvvmshiftlab.vacancy.vacancyDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDao
import ru.shiftlab.mvvmshiftlab.vacancy.database.asDomainModel

class VacancyDetailViewModel(
    vacancyId: Int = 1,
    dataSource: VacancyDao
) : ViewModel() {

    val database = dataSource

    private val viewModelJob = Job()

    private val vacancy = dataSource.getVacancyById(vacancyId)

/*    val id: Int,
    val title: String,
    val salary: String,
    val work_experience: String,
    val date: String,
    val description: String,
    val status_vacancy: Boolean,
    val submit: Boolean,
    val tags: List<String>
    */


    fun getSalaryVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().salary
    }

    fun getTitleVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().title
    }


    fun getVacancy() = Transformations.map(vacancy) {
        it.asDomainModel()
    }

    fun getUrlVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().url
    }

    fun getCityVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().city
    }

    fun getWorkExperienceVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().work_experience
    }

    fun getEmploymentVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().employment
    }

    fun getDateVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().date
    }

    fun getDescriptionVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().description
    }

    private val _navigateToVacancy = MutableLiveData<Boolean?>()
    val navigateToVacancy: LiveData<Boolean?>
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
