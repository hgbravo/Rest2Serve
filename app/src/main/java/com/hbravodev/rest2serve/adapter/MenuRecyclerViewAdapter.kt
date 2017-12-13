package com.hbravodev.rest2serve.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.model.Dish

class MenuRecyclerViewAdapter(val menu: List<Dish>) : RecyclerView.Adapter<MenuRecyclerViewAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DishViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_menu, parent, false)
        return DishViewHolder(view)
    }

    override fun onBindViewHolder(holder: DishViewHolder?, position: Int) {
        holder?.bindDish(menu[position])
    }

    override fun getItemCount() = menu.size

    inner class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val dishImage = itemView.findViewById<ImageView>(R.id.dish_image)
        val dishPrice = itemView.findViewById<TextView>(R.id.dish_price)
        val dishDescription = itemView.findViewById<TextView>(R.id.dish_description)
        val dishAllergen01 = itemView.findViewById<ImageView>(R.id.allergen_01)
        val dishAllergen02 = itemView.findViewById<ImageView>(R.id.allergen_02)
        val dishAllergen03 = itemView.findViewById<ImageView>(R.id.allergen_03)
        val dishAllergen04 = itemView.findViewById<ImageView>(R.id.allergen_04)
        val dishAllergensList: List<ImageView> = listOf(dishAllergen01, dishAllergen02, dishAllergen03, dishAllergen04)

        fun bindDish(dish: Dish) {
            // Get the context so can use getString
            val context = itemView.context

            // Update view - model
            //dishImage.setImageResource(dish.image)
            dishPrice.text = context.getString(R.string.dish_price_format, dish.price)
            dishDescription.text = dish.description

            // Todo: Do better code here. Not Doom Pyramid
            val allergens = dish.allergens?.size
            if (allergens != null) {
                for (item in 0 until allergens) {
                    when(item) {
                        1 -> dishAllergensList[item].setImageResource(R.drawable.ic_001)
                        2 -> dishAllergensList[item].setImageResource(R.drawable.ic_002)
                        3 -> dishAllergensList[item].setImageResource(R.drawable.ic_003)
                        4 -> dishAllergensList[item].setImageResource(R.drawable.ic_004)
                    }
                }
            }

        }
    }
}