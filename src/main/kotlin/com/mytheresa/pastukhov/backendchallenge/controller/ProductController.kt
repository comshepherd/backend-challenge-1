package com.mytheresa.pastukhov.backendchallenge.controller

import com.mytheresa.pastukhov.backendchallenge.service.ProductService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("products")
class ProductController(
    val productService: ProductService
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getProducts(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) priceLessThan: Int?,
    ) = productService.getProducts(category, priceLessThan)


}