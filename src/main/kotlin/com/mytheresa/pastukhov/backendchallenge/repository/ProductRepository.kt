package com.mytheresa.pastukhov.backendchallenge.repository

import com.mytheresa.pastukhov.backendchallenge.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProductRepository : CrudRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity>