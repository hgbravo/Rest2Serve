package com.hbravodev.rest2serve.model

class Table(val number: Int, var items: MutableList<Dish>?) {

    constructor(number: Int) : this(number, null)

    fun addDish(dish: Dish) {
        items?.add(dish)
    }

    fun check(): Float? {
        return if (items?.size != 0) {
            items?.map { it.price }?.sum()
        } else {
            0.0f
        }
    }

    override fun toString() = number.toString()
}

