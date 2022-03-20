package com.mytheresa.pastukhov.backendchallenge.service

import com.mytheresa.pastukhov.backendchallenge.dto.Price
import com.mytheresa.pastukhov.backendchallenge.dto.ProductResponse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class, MockitoExtension::class)
@SpringBootTest
internal class ProductServiceTest @Autowired constructor(
    val productService: ProductService
) {

    @Test
    fun `get products without filter`() {
        val result = productService.getProducts(null, null)

        val expectedProducts = arrayListOf(
            ProductResponse("000001", "BV Lean leather ankle boots", "boots", Price(89000, 62300, "30%")),
            ProductResponse("000002", "BV Lean leather ankle boots", "boots", Price(99000, 69300, "30%")),
            ProductResponse("000003", "Ashlington leather ankle boots", "boots", Price(71000, 49700, "30%")),
            ProductResponse("000004", "Naima embellished suede sandals", "sandals", Price(79500, 79500)),
            ProductResponse("000005", "Nathane leather sneakers", "sneakers", Price(59000, 59000))
        )
        assertEquals(expectedProducts, result)
    }

    @Test
    fun `get products with category filter`() {
        val result = productService.getProducts("boots", null)

        val expectedProducts = arrayListOf(
            ProductResponse("000001", "BV Lean leather ankle boots", "boots", Price(89000, 62300, "30%")),
            ProductResponse("000002", "BV Lean leather ankle boots", "boots", Price(99000, 69300, "30%")),
            ProductResponse("000003", "Ashlington leather ankle boots", "boots", Price(71000, 49700, "30%"))
        )
        assertEquals(expectedProducts, result)
    }

    @Test
    fun `get products with price filter`() {
        val result = productService.getProducts(null, 60000)

        val expectedProducts = arrayListOf(
            ProductResponse("000005", "Nathane leather sneakers", "sneakers", Price(59000, 59000))
        )
        assertEquals(expectedProducts, result)
    }

    @Test
    fun `get products with category and price filters`() {
        val result = productService.getProducts("boots", 75000)

        val expectedProducts = arrayListOf(
            ProductResponse("000003", "Ashlington leather ankle boots", "boots", Price(71000, 49700, "30%")),
        )
        assertEquals(expectedProducts, result)
    }
}