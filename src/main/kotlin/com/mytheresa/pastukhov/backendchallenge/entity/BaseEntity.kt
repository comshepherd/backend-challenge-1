package com.mytheresa.pastukhov.backendchallenge.entity

import org.springframework.data.util.ProxyUtils
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity<T>(
    @Id val id: T
) {
    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as BaseEntity<*>

        return this.id != null && this.id == other.id
    }

    override fun hashCode(): Int {
        return 25
    }

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }
}