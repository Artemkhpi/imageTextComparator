package ua.khpi.it.comparison.db.repository

import org.springframework.stereotype.Component
import ua.khpi.it.comparison.db.mapper.IntervieweeMapper
import ua.khpi.it.comparison.db.repository.jpa.IntervieweeJpaRepository
import ua.khpi.it.comparison.model.Interviewee

@Component
class IntervieweeRepository(private val repository: IntervieweeJpaRepository,
                            private val mapper: IntervieweeMapper) {
    fun getAll(): MutableSet<Interviewee> {
        return repository.findAll().mapNotNullTo(mutableSetOf()) { mapper.fromJpa(it) }
    }

    fun findById(id: Long): Interviewee? {
        return mapper.fromJpa(repository.findById(id).get())
    }

    fun isExists(interviewee: Interviewee): Boolean {
        return repository.existsByCityAndEducationAndYearOfBirthAndSex(
                city = interviewee.city,
                education = interviewee.education,
                yearOfBirth = interviewee.yearOfBirth,
                sex = interviewee.sex)
    }

    fun find(interviewee: Interviewee): Interviewee? {
        return mapper.fromJpa(repository.findByCityAndEducationAndYearOfBirthAndSex(
                city = interviewee.city,
                education = interviewee.education,
                yearOfBirth = interviewee.yearOfBirth,
                sex = interviewee.sex))
    }

    fun add(interviewee: Interviewee): Interviewee? {
        return mapper.fromJpa(repository.save(mapper.toJpa(interviewee)!!))
    }

    fun update(interviewee: Interviewee): Interviewee? {
        return mapper.fromJpa(repository.save(mapper.toJpa(interviewee)!!))
    }

    fun remove(id: Long) {
        repository.deleteById(id)
    }

    fun getTotalAmount():Long{
        return repository.count()
    }
}