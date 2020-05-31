package ua.khpi.it.comparison.db.repository

import org.springframework.stereotype.Component
import ua.khpi.it.comparison.db.mapper.InterviewMapper
import ua.khpi.it.comparison.db.repository.jpa.InterviewJpaRepository
import ua.khpi.it.comparison.model.Interview

@Component
class InterviewRepository(private val repository: InterviewJpaRepository,
                          private val mapper: InterviewMapper) {
    fun getAll(): MutableSet<Interview> {
        return repository.findAll().mapNotNullTo(mutableSetOf()) { mapper.fromJpa(it) }
    }

    fun findById(id: Long): Interview? {
        return mapper.fromJpa(repository.findById(id).get())
    }

    fun add(interview: Interview): Interview? {
        return mapper.fromJpa(repository.save(mapper.toJpa(interview)!!))
    }

    fun update(interview: Interview): Interview? {
        return mapper.fromJpa(repository.save(mapper.toJpa(interview)!!))
    }

    fun getTotalAmount(): Long{
        return repository.count()
    }

    fun getFinishedAmount(): Long{
        return repository.countAllByIsFinishedIsTrue()
    }

    fun remove(id: Long) {
        repository.deleteById(id)
    }
}
