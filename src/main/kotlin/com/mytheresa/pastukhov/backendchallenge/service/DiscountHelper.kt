package com.mytheresa.pastukhov.backendchallenge.service

import com.mytheresa.pastukhov.backendchallenge.entity.DiscountEntity
import com.mytheresa.pastukhov.backendchallenge.entity.EntityEnum
import com.mytheresa.pastukhov.backendchallenge.entity.ProductEntity
import org.springframework.stereotype.Component

@Component
class DiscountHelper {

    fun applyDiscount(product: ProductEntity, discounts: MutableIterable<DiscountEntity>): DiscountResult {
        val first = discounts
            .sortedByDescending { discount -> discount.discount }
            .firstOrNull { discount -> isDiscountApplicable(product, discount) }
        return doApplyDiscount(product, first)
    }

    private fun isDiscountApplicable(product: ProductEntity, discount: DiscountEntity) =
        when (discount.entity) {
            EntityEnum.CATEGORY -> product.categoryId == discount.categoryId
            EntityEnum.SKU -> product.sku == discount.sku
            else -> throw IllegalArgumentException("Unexpected entity {}" + discount.entity)
        }

    private fun doApplyDiscount(product: ProductEntity, discount: DiscountEntity?): DiscountResult {
        if (discount == null) {
            return DiscountResult(product.price, null)
        }
        val finalPrice = product.price * ((100 - discount.discount) % 100) / 100
        return DiscountResult(finalPrice, discount.discount)
    }
}

class DiscountResult(
    val finalPrice: Int,
    val discountPercentage: Int?,
)