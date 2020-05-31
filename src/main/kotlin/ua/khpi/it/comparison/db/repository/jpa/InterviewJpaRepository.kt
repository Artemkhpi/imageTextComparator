package ua.khpi.it.comparison.db.repository.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ua.khpi.it.comparison.db.entity.InterviewEntity
import ua.khpi.it.comparison.db.entity.ProductDescriptionEntity

@Repository
interface InterviewJpaRepository : CrudRepository<InterviewEntity, Long>{
    fun countAllByIsFinishedIsTrue(): Long
}
