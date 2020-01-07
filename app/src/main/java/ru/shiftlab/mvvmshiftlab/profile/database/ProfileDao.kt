package ru.shiftlab.mvvmshiftlab.profile.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 *
 * Data Access Object или Dao, интерфейс для работы с базой данных
 *
 */

@Dao
interface ProfileDao {

    /**
     * Добавляет [profiles] в БД
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(profiles: List<ProfileEntity>)

    /**
     * Добавляет [profile] в БД
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profile: ProfileEntity)

    /**
     * @return возвращает коллекцию профелей
     */
    @Query("SELECT * FROM profile_table")
    fun getAll() : LiveData<List<ProfileEntity>>

    /**
     * @return возвращает профиль по [id]
     */
    @Query("SELECT * FROM profile_table WHERE id = :id")
    fun getProfile(id: Int) : LiveData<ProfileEntity>


}