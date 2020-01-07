package ru.shiftlab.mvvmshiftlab.vacancy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy

@Entity(tableName = "vacancy_table")
data class VacancyEntity(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "salary")
    val salary: Int,

    @PrimaryKey(autoGenerate = true)
    var id: Int)

fun List<VacancyEntity>.asDomainModel() : List<Vacancy>{
    return map {
        Vacancy(
            id = it.id,
            title = it.title,
            type = it.type,
            salary = it.salary

        )
    }
}

fun VacancyEntity.asDomainModel() : Vacancy {
    return Vacancy(
        id = id,
        title = title,
        type = type,
        salary = salary)
}