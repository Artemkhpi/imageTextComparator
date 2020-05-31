package ua.khpi.it.comparison.db.repository.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ua.khpi.it.comparison.db.entity.InterviewStepEntity
import ua.khpi.it.comparison.db.entity.ProductDescriptionEntity

@Repository
interface InterviewStepJpaRepository : CrudRepository<InterviewStepEntity, Long>
