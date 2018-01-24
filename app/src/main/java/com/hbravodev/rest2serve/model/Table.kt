package com.hbravodev.rest2serve.model

import android.util.Log

class Table {

    private val number: Int = 0
    var items: MutableList<Dish>? = arrayListOf()

    constructor(number: Int, items: MutableList<Dish>?)

    fun dishes(): Array<Dish>? = items?.toTypedArray()

    fun addDish(dish: Dish) {
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

