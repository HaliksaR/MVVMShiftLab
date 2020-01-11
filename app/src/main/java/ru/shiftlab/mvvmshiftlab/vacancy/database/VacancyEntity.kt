package ru.shiftlab.mvvmshiftlab.vacancy.database

import androidx.room.*
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy


class ConvertersVacancy {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}


@Entity(
    tableName = "vacancy_table"
)
@TypeConverters(ConvertersVacancy::class)
data class VacancyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "salary")
    val salary: String,
    @ColumnInfo(name = "work_experience")
    val work_experience: String,
    @ColumnInfo(name = "employment")
    val employment: String,
    @ColumnInfo(name = "date_create")
    val date_create: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "open")
    val open: Boolean,
    @ColumnInfo(name = "tags")
    val tags: List<String>
)


fun List<VacancyEntity>.asDomainModel(): List<Vacancy> {
    return map {
        Vacancy(
            id = it.id,
            title = it.title,
            url = it.url,
            city = it.city,
            salary = it.salary,
            work_experience = it.work_experience,
            employment = it.employment,
            date_create = it.date_create,
            description = it.description,
            open = it.open,
            tags = it.tags
        )
    }
}

fun VacancyEntity.asDomainModel(): Vacancy {
    return Vacancy(
        id = id,
        title = title,
        url = url,
        city = city,
        salary = salary,
        work_experience = work_experience,
        employment = employment,
        date_create = date_create,
        description = description,
        open = open,
        tags = tags
    )
}