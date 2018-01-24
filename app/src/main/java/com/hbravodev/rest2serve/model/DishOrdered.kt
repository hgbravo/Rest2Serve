package com.hbravodev.rest2serve.model

class DishOrdered(name: String, allergens: IntArray?, price: Float, description: String, image: Int, val notes: String): Dish(name, allergens, price, description, image) {

    constructor(dish: Dish, notes: String) : this(dish.name, dish.allergens, dish.price, dish.description, dish.image, notes)
}