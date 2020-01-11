package ru.shiftlab.mvvmshiftlab.profile.domain

data class Profile(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String,
    val photo: String,
    val date_birth: String,
    val gender: String,
    val city: String,
    val phone: String,
    val email: String,
    val subscribe_events_tags: List<String>,
    val subscribe_vacancies_tags: List<String>
)