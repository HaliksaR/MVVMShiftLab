package ru.shiftlab.mvvmshiftlab.profile.domain

/**
 * Класс профиля
 * @param[id] индификатор профиля
 * @param[name] имя пользователя
 * @param[status] статус пользователя (лаборант, куратор и т.п.)
 * @param[specialty] специальность пользователя (Android разработчик и т.п.)
 *
 */
data class Profile(val id: Int,
                   val name: String,
                   val status: String,
                   val specialty: String)

