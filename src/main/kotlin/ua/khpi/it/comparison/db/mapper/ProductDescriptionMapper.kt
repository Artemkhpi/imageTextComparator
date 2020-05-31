package ua.khpi.it.comparison.db.mapper

import org.springframework.stereotype.Service
import ua.khpi.it.comparison.db.entity.ProductDescriptionEntity
import ua.khpi.it.comparison.model.ProductDescription

@Service
class ProductDescriptionMapper {
    fun toJpa(productDescription: ProductDescription?): ProductDescriptionEntity? {
        if (productDescription == null) return null
        productDescription.let {
            return ProductDescriptionEntity(
                    descriptionId = it.descriptionId,
                    content = it.content,
                    correctImageName = it.correctImageName
            )
        }
    }

    fun fromJpa(productDescription: ProductDescriptionEntity?): ProductDescription? {
        if (productDescription == null) return null
        productDescription.let {
            return ProductDescription(
                    descriptionId = it.descriptionId,
                    content = it.content,
                    correctImageName = it.correctImageName
            )
        }
    }
}
