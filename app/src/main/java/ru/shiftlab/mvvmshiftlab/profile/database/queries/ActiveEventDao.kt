package ru.shiftlab.mvvmshiftlab.profile.database.queries

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ActiveEventEntity

@Dao
interface ActiveEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(active_events: List<ActiveEventEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActiveEvent(active_event: ActiveEventEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateActiveEvent(active_event: ActiveEventEntity)

    @Delete
    suspend fun deleteActiveEvent(active_event: ActiveEventEntity)

    @Query("SELECT * FROM active_events")
    fun getAll(): LiveData<List<ActiveEventEntity>>

}