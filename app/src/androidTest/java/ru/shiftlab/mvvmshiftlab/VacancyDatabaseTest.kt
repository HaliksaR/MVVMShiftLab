package ru.shiftlab.mvvmshiftlab

import android.util.Log
import androidx.lifecycle.Transformations
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ru.shiftlab.mvvmshiftlab.profile.database.ProfileDatabase
import ru.shiftlab.mvvmshiftlab.profile.database.entities.ProfileEntity
import ru.shiftlab.mvvmshiftlab.profile.database.queries.ProfileHierarchyDao
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class VacancyDatabaseTest {

    private lateinit var profileHierarchyDao: ProfileHierarchyDao
    private lateinit var profileDatabase: ProfileDatabase

    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        profileDatabase = Room.inMemoryDatabaseBuilder(context, ProfileDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        profileHierarchyDao = profileDatabase.profileHierarchyDao
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        profileDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetVacancy() {
        profileHierarchyDao.insertProfile(
            ProfileEntity(
                9,
                "TEST_NAME",
                "TEST_STATUS",
                "TEST_SPEC"
            )
        )

        val profile = profileHierarchyDao.getProfile(9)
        val profileString = Transformations.map(profile) {
            it.asDomainModel()
        }

        Log.d("TEST", "${profileString.value}")
    }
}