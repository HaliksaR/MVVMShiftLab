package ru.shiftlab.mvvmshiftlab.vacancy.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.shiftlab.mvvmshiftlab.vacancy.database.VacancyDao

class VacancyViewModelFactory(
    private val database: VacancyDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VacancyViewModel::class.java)) {
            return VacancyViewModel(
                database,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}