package com.hbravodev.rest2serve.model

import java.io.Serializable
import java.util.*

data class Dish(val name: String, val allergens: IntArray?, val price: Float, val description: String, val image: Int) : Serializable {

    override fun toString() = "$name, $$price"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dish

        if (name != other.name) return false
        if (!Arrays.equals(allergens, other.allergens)) return false
        if (price != other.price) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (allergens?.let { Arrays.hashCode(it) } ?: 0)
        result = 31 * result + price.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}