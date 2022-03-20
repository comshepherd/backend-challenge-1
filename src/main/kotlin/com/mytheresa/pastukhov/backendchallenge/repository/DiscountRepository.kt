package com.mytheresa.pastukhov.backendchallenge.repository

import com.mytheresa.pastukhov.backendchallenge.entity.DiscountEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DiscountRepository : CrudRepository<DiscountEntity, UUID>