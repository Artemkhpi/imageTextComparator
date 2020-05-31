package ua.khpi.it.comparison.db.repository

import org.springframework.stereotype.Component
import ua.khpi.it.comparison.db.mapper.InterviewStepMapper
import ua.khpi.it.comparison.db.repository.jpa.InterviewStepJpaRepository
import ua.khpi.it.comparison.model.InterviewStep

@Component
class InterviewStepRepository(private val repository: InterviewStepJpaRepository,
                              private val mapper: InterviewStepMapper) {
    fun getAll(): MutableSet<InterviewStep> {
        return repository.findAll().mapNotNullTo(mutableSetOf()) { mapper.fromJpa(it) }
    }

    fun findById(id: Long): InterviewStep? {
        return mapper.fromJpa(repository.findById(id).get())
    }

    fun add(interviewStep: InterviewStep): InterviewStep? {
        return mapper.fromJpa(repository.save(mapper.toJpa(interviewStep)!!))
    }

    fun update(interviewStep: InterviewStep): InterviewStep? {
        return mapper.fromJpa(repository.save(mapper.toJpa(interviewStep)!!))
    }

    fun remove(id: Long) {
        repository.deleteById(id)
    }
}
