package ru.shiftlab.mvvmshiftlab

import ru.shiftlab.mvvmshiftlab.profile.domain.Profile
import ru.shiftlab.mvvmshiftlab.vacancy.domain.Vacancy

fun vacancyFormat(vacancies: List<Vacancy>) : String {

    return vacancies.toString()
}

fun profileFormat(profiles: List<Profile>) : String {
    return profiles.toString()
}


fun vacancyFormat(vacancy: Vacancy?) : String {

    return vacancy.toString()
}

