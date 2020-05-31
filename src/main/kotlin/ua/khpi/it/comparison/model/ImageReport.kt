package ua.khpi.it.comparison.model

data class ImageReport(
        val description: String,
        val user: Interviewee,
        val usedAnswer: Boolean,
        val predefinedAnswer: Boolean
)