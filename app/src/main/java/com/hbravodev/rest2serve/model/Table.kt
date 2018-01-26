package com.hbravodev.rest2serve.model

import android.util.Log

class Table(val number: Int) {


    var items: MutableList<DishOrdered>? = arrayListOf()

//    constructor(number: Int, items: MutableList<DishOrdered>?)

    fun dishes(): Array<DishOrdered>? = items?.toTypedArray()

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

