package ua.khpi.it.comparison.service

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.repository.InterviewRepository
import ua.khpi.it.comparison.exception.ApiException
import ua.khpi.it.comparison.model.Interview
import ua.khpi.it.comparison.model.ProductDescription

@Service
class InterviewService(private val interviewRepository: InterviewRepository,
                       private val interviewStepService: InterviewStepService) {
    fun getAll(): MutableSet<Interview> {
        return interviewRepository.getAll()
    }

    fun findByIntervieweeId(): MutableSet<Interview> {
        return interviewRepository.getAll()
    }

    fun getTotalAmount(): Long {
        return interviewRepository.getTotalAmount()
    }

    fun getFinishedAmount(): Long {
        return interviewRepository.getFinishedAmount()
    }

    fun findById(id: Long): Interview? {
        return interviewRepository.findById(id)
    }

    fun add(interview: Interview): Interview {
        return interviewRepository.add(interview)!!
    }

    fun update(interview: Interview): Interview {
        return interviewRepository.update(interview)!!
    }

    fun remove(id: Long) {
        interviewRepository.remove(id)
    }

    fun createStepsForInterview(steps: Int, descriptions: MutableList<ProductDescription>): ArrayList<Long> {
        val interviewStepsId = arrayListOf<Long>()
        if (descriptions.isNullOrEmpty()) {
            throw ApiException(message = "Not enough data for interview")
        }
        for (i in 0 until steps) {
            interviewStepsId.add(
                    interviewStepService.createInterviewStep(
                            productDescription = descriptions[getLimitedIndex(limit = descriptions.size, index = i)]
                    ).stepId!!)
        }
        return interviewStepsId
    }

    private fun getLimitedIndex(limit: Int, index: Int): Int {
        var resultIndex = index
        while (resultIndex >= limit) {
            if (resultIndex < limit)
                return limit
            resultIndex -= limit
        }
        return resultIndex
    }

    fun checkIfInterviewStepDataIsValid(interview: Interview?, interviewStepId: Long) {
        if (interview == null || !interview.interviewStepsIdList.contains(interviewStepId) || interviewStepService.findById(interviewStepId) == null) {
            throw ApiException("Interview step data is invalid")
        }
    }

    fun checkIfInterviewIsFinished(interview: Interview, interviewStepId: Long) {
        if (interview.isFinished) {
            throw ApiException("Interview is already finished")
        }
        if (interview.completedInterviewStepsIdList.contains(interviewStepId)) {
            throw ApiException("Interview step is already finished")
        }
    }

    fun handleFinishedStep(interview: Interview, interviewStepId: Long): Boolean {
        interview.completedInterviewStepsIdList.add(interviewStepId)
        interview.interviewStepsIdList.remove(interviewStepId)
        interview.isFinished = interview.interviewStepsIdList.isEmpty()
        update(interview)
        return interview.isFinished
    }
}
