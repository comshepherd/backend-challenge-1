package com.mytheresa.pastukhov.backendchallenge.service

import com.mytheresa.pastukhov.backendchallenge.dto.Price
import com.mytheresa.pastukhov.backendchallenge.dto.ProductResponse
import com.mytheresa.pastukhov.backendchallenge.repository.DiscountRepository
import com.mytheresa.pastukhov.backendchallenge.repository.ProductRepository
import com.mytheresa.pastukhov.backendchallenge.repository.buildSpecificationFilter
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val discountRepository: DiscountRepository,
    private val discountHelper: DiscountHelper
) {

    companion object {
        private val PRODUCT_LIMIT: PageRequest = PageRequest.of(0, 5)
    }

    @Transactional(readOnly = true)
    fun getProducts(category: String?, priceLessThan: Int?): List<ProductResponse> {
        val discounts = discountRepository.findAll()

        val specificationFilter = buildSpecificationFilter(category, priceLessThan)
        val products = productRepository.findAll(specificationFilter, PRODUCT_LIMIT)

        return products.toList()
            .map { product ->
                val discount = discountHelper.applyDiscount(product, discounts)
                val price = Price(
                    product.price,
                    discount.finalPrice,
                    discount.discountPercentage?.let { "$it%" }
                )
                ProductResponse(product.sku, product.name, product.category.code, price)
            }
    }

}