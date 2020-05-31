package ua.khpi.it.comparison.service

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.repository.ProductDescriptionRepository
import ua.khpi.it.comparison.exception.ApiException
import ua.khpi.it.comparison.model.ProductDescription

@Service
class ProductDescriptionService(private val productDescriptionRepository: ProductDescriptionRepository) {
    fun getAll(): MutableSet<ProductDescription> {
        return productDescriptionRepository.getAll().toMutableSet()
    }

    fun getRandomRecords(amount: Int): MutableList<ProductDescription> {
        if (amount > getTotalAmount()) {
            throw ApiException("Not enough data for interview")
        }
        return productDescriptionRepository.getRandomRecords(amount).toMutableList()
    }

    fun findById(id: Long): ProductDescription {
        return productDescriptionRepository.findById(id)!!
    }

    fun add(product: ProductDescription): ProductDescription {
        return productDescriptionRepository.add(product)!!
    }

    fun remove(id: Long) {
        productDescriptionRepository.remove(id)
    }

    fun getTotalAmount(): Long {
        return productDescriptionRepository.getTotalAmount()
    }
}
