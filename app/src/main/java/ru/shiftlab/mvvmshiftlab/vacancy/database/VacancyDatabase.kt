package ru.shiftlab.mvvmshiftlab.vacancy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [VacancyEntity::class], version = 2, exportSchema = false)
abstract class VacancyDatabase : RoomDatabase() {

    abstract val vacancyDao: VacancyDao

    companion object {

        @Volatile
        private var INSTANCE: VacancyDatabase? = null

        fun getInstance(context: Context): VacancyDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        VacancyDatabase::class.java,
                        "vacancy_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}