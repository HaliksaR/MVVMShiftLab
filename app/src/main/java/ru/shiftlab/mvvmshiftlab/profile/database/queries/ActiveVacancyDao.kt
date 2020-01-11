package ru.shiftlab.mvvmshiftlab.profile.database.queries

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ActiveVacancyEntity

@Dao
interface ActiveVacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(active_vacancies: List<ActiveVacancyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActiveVacancy(active_vacancy: ActiveVacancyEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActiveVacancy(active_vacancy: ActiveVacancyEntity)

    @Delete
    suspend fun deleteActiveVacancy(active_vacancy: ActiveVacancyEntity)

    @Query("SELECT * FROM active_vacancies")
    fun getAll(): LiveData<List<ActiveVacancyEntity>>

}