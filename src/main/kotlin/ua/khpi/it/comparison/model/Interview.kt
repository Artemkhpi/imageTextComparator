package ua.khpi.it.comparison.model

data class Interview(
        var interviewId: Long? = null,
        var steps: Int,
        var intervieweeId: Long,
        var interviewStepsIdList: MutableList<Long> = mutableListOf(),
        var completedInterviewStepsIdList: MutableList<Long> = mutableListOf(),
        var isFinished: Boolean = false
)
