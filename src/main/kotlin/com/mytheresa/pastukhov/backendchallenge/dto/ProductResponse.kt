package com.mytheresa.pastukhov.backendchallenge.dto

data class ProductResponse(
    val sku: String,
    val name: String,
    val category: String,
    val price: Price,
)

data class Price(
    val original: Int,
    val final: Int,
    val discountPercentage: String? = null,
    val currency: String = "EUR",
)
