package com.mytheresa.pastukhov.backendchallenge.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "categories")
class CategoryEntity(
    val code: String
) : BaseEntity<UUID>(UUID.randomUUID())

