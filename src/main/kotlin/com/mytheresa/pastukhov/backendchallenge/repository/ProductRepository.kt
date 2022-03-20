package com.mytheresa.pastukhov.backendchallenge.repository

import com.mytheresa.pastukhov.backendchallenge.entity.CategoryEntity
import com.mytheresa.pastukhov.backendchallenge.entity.ProductEntity
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.criteria.Join
import javax.persistence.criteria.JoinType

@Repository
interface ProductRepository : CrudRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity>

fun buildSpecificationFilter(category: String?, priceLessThan: Int?): Specification<ProductEntity> {
    return Specification<ProductEntity> { root, query, builder ->
        val categoryPredicate = if (category != null && category.isNotBlank()) {
            val categoryJoin: Join<ProductEntity, CategoryEntity> = root.join("category", JoinType.INNER)
            builder.like(builder.lower(categoryJoin.get("code")), "%${category.lowercase()}%")
        } else {
            builder.and()
        }

        val pricePredicate = if (priceLessThan != null) {
            builder.lessThanOrEqualTo(root.get("price"), priceLessThan)
        } else {
            builder.and()
        }

        builder.and(categoryPredicate, pricePredicate)
    }
}