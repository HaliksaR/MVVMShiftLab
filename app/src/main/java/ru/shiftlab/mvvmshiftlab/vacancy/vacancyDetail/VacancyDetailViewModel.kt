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


    fun getVacancy() = Transformations.map(vacancy) {
        it.asDomainModel()
    }

    fun getTitleVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().title
    }

    fun getUrlVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().url
    }

    fun getCityVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().city
    }

    fun getSalaryVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().salary
    }

    fun getWorkExperienceVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().work_experience
    }

    fun getEmploymentVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().employment
    }

    fun getDateСreateVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().date_create
    }

    fun getDescriptionVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().description
    }

    fun getOpenVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().open
    }

    fun getTagsVacancy() = Transformations.map(vacancy) {
        it.asDomainModel().tags
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
