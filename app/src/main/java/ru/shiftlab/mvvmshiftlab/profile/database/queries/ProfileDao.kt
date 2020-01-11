package ru.shiftlab.mvvmshiftlab.profile.database.queries


import androidx.lifecycle.LiveData
import androidx.room.*
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ProfileEntity

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProfile(profile: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)

    @Query("select * from profile_table")
    fun getProfile(): LiveData<ProfileEntity>

}