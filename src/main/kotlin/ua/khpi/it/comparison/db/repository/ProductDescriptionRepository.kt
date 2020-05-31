package ua.khpi.it.comparison.db.repository

import org.springframework.stereotype.Component
import ua.khpi.it.comparison.db.mapper.ProductDescriptionMapper
import ua.khpi.it.comparison.db.repository.jpa.ProductDescriptionJpaRepository
import ua.khpi.it.comparison.model.ProductDescription

@Component
class ProductDescriptionRepository(private val repository: ProductDescriptionJpaRepository,
                                   private val mapper: ProductDescriptionMapper) {
    fun getAll(): MutableList<ProductDescription> {
        return repository.findAll().mapNotNullTo(mutableListOf()) { mapper.fromJpa(it) }
    }

    fun getRandomRecords(amount: Int): MutableSet<ProductDescription> {
        return repository.getRandomRecords(amount).mapNotNullTo(mutableSetOf()) { mapper.fromJpa(it) }
    }

    fun findById(id: Long): ProductDescription? {
        return mapper.fromJpa(repository.findById(id).get())
    }

    fun add(productDescription: ProductDescription): ProductDescription? {
        return mapper.fromJpa(repository.save(mapper.toJpa(productDescription)!!))
    }

    fun update(productDescription: ProductDescription): ProductDescription? {
        return mapper.fromJpa(repository.save(mapper.toJpa(productDescription)!!))
    }

    fun remove(id: Long) {
        repository.deleteById(id)
    }

    fun getTotalAmount(): Long {
        return repository.count()
    }
}
