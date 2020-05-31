package ua.khpi.it.comparison.model

data class InterviewStep(
        var stepId: Long? = null,
        var imageName: String? = null,
        var descriptionId: Long,
        var userAnswer: Boolean? = null,
        var correctAnswer: Boolean? = null
)
