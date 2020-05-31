package ua.khpi.it.comparison.db.entity

import javax.persistence.*

@Entity
@Table(name = "product_description")
data class ProductDescriptionEntity(
        @Id
        @Column(name = "description_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var descriptionId: Long?,
        @Column(name = "content")
        var content: String,
        @Column(name = "correct_image_name")
        var correctImageName: String
)
