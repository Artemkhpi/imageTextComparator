package ua.khpi.it.comparison.db.mapper

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.entity.InterviewStepEntity
import ua.khpi.it.comparison.model.InterviewStep

@Service
class InterviewStepMapper {
    fun toJpa(interviewStep: InterviewStep?): InterviewStepEntity? {
        if (interviewStep == null) return null
        interviewStep.let {
            return InterviewStepEntity(
                    stepId = it.stepId,
                    imageName = it.imageName!!,
                    descriptionId = it.descriptionId,
                    userAnswer = it.userAnswer,
                    correctAnswer = it.correctAnswer!!
            )
        }
    }

    fun fromJpa(interviewStepEntity: InterviewStepEntity?): InterviewStep? {
        if (interviewStepEntity == null) return null
        interviewStepEntity.let {
            return InterviewStep(
                    stepId = it.stepId,
                    imageName = it.imageName,
                    descriptionId = it.descriptionId,
                    userAnswer = it.userAnswer,
                    correctAnswer = it.correctAnswer
            )
        }
    }
}
