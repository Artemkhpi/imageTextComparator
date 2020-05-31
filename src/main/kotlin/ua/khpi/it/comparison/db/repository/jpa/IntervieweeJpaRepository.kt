package ua.khpi.it.comparison.db.repository.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ua.khpi.it.comparison.db.entity.IntervieweeEntity
import ua.khpi.it.comparison.model.Interviewee
import ua.khpi.it.comparison.model.Sex
import java.sql.Date

@Repository
interface IntervieweeJpaRepository : CrudRepository<IntervieweeEntity, Long> {
    fun existsByCityAndEducationAndYearOfBirthAndSex(city: String, education: String, yearOfBirth: Long, sex: Sex): Boolean
    fun findByCityAndEducationAndYearOfBirthAndSex(city: String, education: String, yearOfBirth: Long, sex: Sex): IntervieweeEntity
}