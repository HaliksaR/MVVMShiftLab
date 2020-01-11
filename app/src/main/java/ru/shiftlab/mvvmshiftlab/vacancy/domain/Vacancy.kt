package ru.shiftlab.mvvmshiftlab.vacancy.domain


/*
data class Vacancy(
    val title: String,
    val type: String,
    val salary: Int,
    var id: Int
) {

    override fun toString(): String {
        return """
            title = $title
            type = $type
            salary = $salary
            id = $id
        """.trimIndent()
    }
}*/

data class Vacancy(
    val id: Int,
    val title: String,
    val url: String,
    val city: String,
    val salary: String,
    val work_experience: String,
    val employment: String,
    val date_create: String,
    val description: String,
    val open: Boolean,
    val tags: List<String>
) {
    override fun toString(): String {
        return """
            id = $id,
            title = $title,
            url = $url,
            city = $city,
            salary = $salary,
            work_experience = $work_experience,
            employment = $employment,
            date_create = $date_create,
            description = $description,
            open = $open, 
            tags = $tags
        """.trimIndent()
    }
}