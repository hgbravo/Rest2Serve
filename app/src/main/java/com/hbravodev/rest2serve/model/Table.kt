package com.hbravodev.rest2serve.model

import android.util.Log

class Table {

    private val number: Int = 0
    var items: MutableList<DishOrdered>? = arrayListOf()

    constructor(number: Int, items: MutableList<DishOrdered>?)

    fun dishes(): Array<Dish>? = items?.toTypedArray()

    fun addDish(dish: DishOrdered) {
        Log.v("TAG", "${items?.add(dish)}")
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

