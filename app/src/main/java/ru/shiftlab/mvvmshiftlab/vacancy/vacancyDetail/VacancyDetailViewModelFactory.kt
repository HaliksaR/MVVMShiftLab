package ru.shiftlab.mvvmshiftlab.vacancy.vacancyDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDao
import java.lang.IllegalArgumentException

class VacancyDetailViewModelFactory(private val vacancyId: Int,
                                    private val dataSource: VacancyDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyDetailViewModel::class.java)) {
            return VacancyDetailViewModel(
                vacancyId,
                dataSource
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}