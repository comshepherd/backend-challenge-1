package com.mytheresa.pastukhov.backendchallenge.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products")
class ProductEntity(
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
) : BaseEntity<UUID>(UUID.randomUUID())