package com.mytheresa.pastukhov.backendchallenge.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "discounts")
class DiscountEntity(
    @Id val id: UUID = UUID.randomUUID(),
    @Enumerated(EnumType.STRING) var entity: EntityEnum,
    val sku: String?,
    val categoryId: UUID?,
    val discount: Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DiscountEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

enum class EntityEnum {
    CATEGORY, SKU
}

