package com.mytheresa.pastukhov.backendchallenge.service

import com.mytheresa.pastukhov.backendchallenge.dto.Price
import com.mytheresa.pastukhov.backendchallenge.dto.ProductResponse
import com.mytheresa.pastukhov.backendchallenge.entity.CategoryEntity
import com.mytheresa.pastukhov.backendchallenge.entity.ProductEntity
import com.mytheresa.pastukhov.backendchallenge.repository.DiscountRepository
import com.mytheresa.pastukhov.backendchallenge.repository.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.criteria.Join
import javax.persistence.criteria.JoinType

@Service
class ProductService(
    val productRepository: ProductRepository,
    val discountRepository: DiscountRepository,
    val discountHelper: DiscountHelper
) {

    @Transactional(readOnly = true)
    fun getProducts(category: String?, priceLessThan: Int?): List<ProductResponse> {
        val discounts = discountRepository.findAll()
        val products = productRepository.findAll(buildSpecificationFilters(category, priceLessThan), LIMIT)

        return products.toList()
            .map { product ->
                val discount = discountHelper.applyDiscount(product, discounts)
                val price = Price(
                    discount.originalPrice,
                    discount.finalPrice,
                    discount.discountPercentage?.let { "$it%" }
                )
                ProductResponse(product.sku, product.name, product.category.code, price)
            }
    }

    private fun buildSpecificationFilters(category: String?, priceLessThan: Int?): Specification<ProductEntity> {
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

    companion object {
        val LIMIT: PageRequest = PageRequest.of(0, 5)
    }

}