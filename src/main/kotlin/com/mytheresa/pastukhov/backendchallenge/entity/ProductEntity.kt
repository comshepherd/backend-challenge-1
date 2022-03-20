package com.mytheresa.pastukhov.backendchallenge.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products")
class ProductEntity(
    @Id val id: UUID,
    val sku: String,
    val name: String,
    val categoryId: UUID,
    val price: Int,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "categoryId",
        insertable = false,
        updatable = false
    )
    val category: CategoryEntity
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}