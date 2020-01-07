package ru.shiftlab.mvvmshiftlab.vacancy.domain


data class Vacancy(

    val title: String,
    val type: String,
    val salary: Int,
    var id: Int) {

    override fun toString(): String {
        return """
            title = $title
            type = $type
            salary = $salary
            id = $id
        """.trimIndent()
    }
}