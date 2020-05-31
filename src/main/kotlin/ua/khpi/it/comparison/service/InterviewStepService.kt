package ua.khpi.it.comparison.service

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.repository.InterviewStepRepository
import ua.khpi.it.comparison.model.InterviewStep
import ua.khpi.it.comparison.model.ProductDescription
import ua.khpi.it.comparison.service.storage.StorageService
import kotlin.random.Random

@Service
class InterviewStepService(private val interviewStepRepository: InterviewStepRepository,
                           private val storageService: StorageService) {
    fun getAll(): MutableSet<InterviewStep> {
        return interviewStepRepository.getAll()
    }

    fun findById(id: Long): InterviewStep? {
        return interviewStepRepository.findById(id)
    }

    fun add(interviewStep: InterviewStep): InterviewStep {
        return interviewStepRepository.add(interviewStep)!!
    }

    fun update(interviewStep: InterviewStep): InterviewStep {
        return interviewStepRepository.update(interviewStep)!!
    }

    fun remove(id: Long) {
        interviewStepRepository.remove(id)
    }

    fun createInterviewStep(productDescription: ProductDescription): InterviewStep {
        val interviewStep = InterviewStep(descriptionId = productDescription.descriptionId!!)
        if (Random.nextBoolean()) {
            interviewStep.imageName = productDescription.correctImageName
            interviewStep.correctAnswer = true
        } else {
            interviewStep.correctAnswer = false
            interviewStep.imageName = storageService.getRandomFilename()
        }
        return interviewStepRepository.add(interviewStep)!!
    }
}
