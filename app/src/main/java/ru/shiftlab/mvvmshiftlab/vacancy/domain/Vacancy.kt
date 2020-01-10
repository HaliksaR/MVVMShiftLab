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
    val date: String,
    val description: String,
    val status_vacancy: Boolean,
    val submit: Boolean,
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
            date = $date,
            description = $description,
            status_vacancy = $status_vacancy,
            submit = $submit, 
            tags = $tags
        """.trimIndent()
    }
}