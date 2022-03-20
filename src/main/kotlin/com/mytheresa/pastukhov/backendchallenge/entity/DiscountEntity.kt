package com.mytheresa.pastukhov.backendchallenge.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "discounts")
class DiscountEntity(
    @Enumerated(EnumType.STRING) val entity: EntityEnum,
    val sku: String?,
    val categoryId: UUID?,
    val discount: Int
) : BaseEntity<UUID>(UUID.randomUUID())

enum class EntityEnum {
    CATEGORY, SKU
}

