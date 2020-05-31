package ua.khpi.it.comparison.db.mapper

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.entity.IntervieweeEntity
import ua.khpi.it.comparison.model.Interviewee

@Service
class IntervieweeMapper {
    fun toJpa(interviewee: Interviewee?): IntervieweeEntity? {
        if (interviewee == null) return null
        interviewee.let {
            return IntervieweeEntity(
                    intervieweeId = it.intervieweeId,
                    city = it.city,
                    education = it.education,
                    yearOfBirth = it.yearOfBirth,
                    sex = it.sex
            )
        }
    }

    fun fromJpa(intervieweeEntity: IntervieweeEntity?): Interviewee? {
        if (intervieweeEntity == null) return null
        intervieweeEntity.let {
            return Interviewee(
                    intervieweeId = it.intervieweeId,
                    city = it.city,
                    education = it.education,
                    yearOfBirth = it.yearOfBirth,
                    sex = it.sex
            )
        }
    }
}
