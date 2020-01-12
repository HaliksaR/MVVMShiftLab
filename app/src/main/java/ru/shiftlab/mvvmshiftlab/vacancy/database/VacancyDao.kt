package ru.shiftlab.mvvmshiftlab.vacancy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VacancyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vacancies: List<VacancyEntity>)

    @Query("select * from vacancy_table where id =:key")
    fun getVacancyById(key: Int): LiveData<VacancyEntity>

    @Query("delete from vacancy_table")
    fun clear()

    @Query("select * from vacancy_table")
    fun getAll(): LiveData<List<VacancyEntity>>
}