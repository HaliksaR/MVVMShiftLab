package ru.shiftlab.mvvmshiftlab.profile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.shiftlab.mvvmshiftlab.profile.database.converters.ListStringConverter
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ActiveEventEntity
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ActiveVacancyEntity
import ru.shiftlab.mvvmshiftlab.profile.database.entities.NotificationChannelsEntity
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ProfileEntity
import ru.shiftlab.mvvmshiftlab.profile.database.queries.ActiveEventDao
import ru.shiftlab.mvvmshiftlab.profile.database.queries.ActiveVacancyDao
import ru.shiftlab.mvvmshiftlab.profile.database.queries.NotificationChannelsDao
import ru.shiftlab.mvvmshiftlab.profile.database.queries.ProfileDao


/*
https://github.com/tfiers/zorro/tree/89d131113a99563f30da50e4112e0f39106b9a87/android/app/src/main/java/net/tomasfiers/zorro/data/entities
https://habr.com/ru/post/349280/
https://startandroid.ru/ru/courses/dagger-2/27-course/architecture-components/530-urok-6-room-entity.html

 */
@Database(
    entities = [
        ProfileEntity::class,
        ActiveEventEntity::class,
        ActiveVacancyEntity::class,
        NotificationChannelsEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao
    abstract fun activeEventDao(): ActiveEventDao
    abstract fun activeVacancyDao(): ActiveVacancyDao
    abstract fun notificationChannelsDao(): NotificationChannelsDao

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getInstance(context: Context): ProfileDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProfileDatabase::class.java,
                        "profile_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}