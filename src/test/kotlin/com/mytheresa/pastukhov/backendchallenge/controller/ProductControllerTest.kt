package com.mytheresa.pastukhov.backendchallenge.controller

import com.mytheresa.pastukhov.backendchallenge.util.TestUtil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class, MockitoExtension::class)
internal class ProductControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
) {

    @Test
    fun `should return 5 products`() {
        mockMvc.get("/products")
            .andDo { print() }
            .andExpect {
                status().isOk()
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(TestUtil.readFileUsingGetResource("/test/json/products_response.json"))
                }
            }

    }
}