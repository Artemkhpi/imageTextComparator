package ua.khpi.it.comparison.db.mapper

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.entity.InterviewEntity
import ua.khpi.it.comparison.model.Interview

@Service
class InterviewMapper {
    fun toJpa(interview: Interview?): InterviewEntity? {
        if (interview == null) return null
        interview.let {
            return InterviewEntity(
                    interviewId = it.interviewId,
                    intervieweeId = it.intervieweeId,
                    steps = it.steps,
                    isFinished = it.isFinished,
                    interviewStepsIdList = it.interviewStepsIdList,
                    completedInterviewStepsIdList = it.completedInterviewStepsIdList
            )
        }
    }

    fun fromJpa(interviewEntity: InterviewEntity?): Interview? {
        if (interviewEntity == null) return null
        interviewEntity.let {
            return Interview(
                    interviewId = it.interviewId,
                    intervieweeId = it.intervieweeId,
                    steps = it.steps,
                    isFinished = it.isFinished,
                    interviewStepsIdList = it.interviewStepsIdList,
                    completedInterviewStepsIdList = it.completedInterviewStepsIdList
            )
        }
    }
}
