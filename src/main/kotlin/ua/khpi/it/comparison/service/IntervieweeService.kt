package ua.khpi.it.comparison.service

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.repository.IntervieweeRepository
import ua.khpi.it.comparison.model.Interviewee

@Service
class IntervieweeService(private val intervieweeRepository: IntervieweeRepository) {
    fun getAll(): MutableSet<Interviewee> {
        return intervieweeRepository.getAll()
    }

    fun findById(id: Long): Interviewee {
        return intervieweeRepository.findById(id)!!
    }

    fun isExists(interviewee: Interviewee): Boolean{
        return intervieweeRepository.isExists(interviewee)
    }

    fun find(interviewee: Interviewee): Interviewee? {
        return intervieweeRepository.find(interviewee)!!
    }

    fun add(interviewee: Interviewee): Interviewee {
        return intervieweeRepository.add(interviewee)!!
    }

    fun remove(id: Long) {
        intervieweeRepository.remove(id)
    }

    fun getTotalAmount(): Long{
        return intervieweeRepository.getTotalAmount()
    }

    fun findExistingOrCreate(interviewee: Interviewee): Interviewee{
        return if (isExists(interviewee)) {
            find(interviewee)!!
        } else {
            add(interviewee)
        }
    }
}
