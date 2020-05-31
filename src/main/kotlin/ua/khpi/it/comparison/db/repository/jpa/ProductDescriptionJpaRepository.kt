package ua.khpi.it.comparison.db.repository.jpa

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ua.khpi.it.comparison.db.entity.ProductDescriptionEntity

@Repository
interface ProductDescriptionJpaRepository : CrudRepository<ProductDescriptionEntity, Long>{
    @Query(nativeQuery=true, value="SELECT *  FROM product_description ORDER BY RAND() LIMIT :amount")
    fun getRandomRecords(amount: Int): List<ProductDescriptionEntity>
}
