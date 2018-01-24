package com.hbravodev.rest2serve.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.model.Dish
import com.hbravodev.rest2serve.model.DishOrdered
import kotlinx.android.synthetic.main.fragment_dish_detail.*

class DishDetailFragment : Fragment() {

    companion object {
        private val ARG_DISH = "ARG_DISH"

        fun newInstance(dish: Dish): DishDetailFragment {
            val fragment = DishDetailFragment()

            val args = Bundle()
            args.putSerializable(ARG_DISH, dish)
            fragment.arguments = args

            return fragment
        }
    }

    private var onAddDishListener: OnAddDishListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater?.inflate(R.layout.fragment_dish_detail, container, false)

        val dishName = root?.findViewById<TextView>(R.id.dish_name)
        val dishImage = root?.findViewById<ImageView>(R.id.dish_image)
        val dishPrice = root?.findViewById<TextView>(R.id.dish_price)
        val dishAllergen01 = root?.findViewById<ImageView>(R.id.allergen_01)
        val dishAllergen02 = root?.findViewById<ImageView>(R.id.allergen_02)
        val dishAllergen03 = root?.findViewById<ImageView>(R.id.allergen_03)
        val dishAllergen04 = root?.findViewById<ImageView>(R.id.allergen_04)
        val dishAllergensList: List<ImageView?> = listOf(dishAllergen01, dishAllergen02, dishAllergen03, dishAllergen04)
        val dishDescription = root?.findViewById<TextView>(R.id.dish_description)
        val addDishBtn = root?.findViewById<Button>(R.id.add_dish_btn)
        val cancelBtn = root?.findViewById<Button>(R.id.cancel_btn)
        val dishNotes = root?.findViewById<EditText>(R.id.dish_notes)

        val dishToShow = arguments.getSerializable(ARG_DISH) as? Dish

        if (dishToShow != null) {
            dishName?.text = dishToShow.name
            dishImage?.setImageResource(dishToShow.dishImage())
            dishPrice?.text = getString(R.string.dish_price_format, dishToShow.price)
            dishDescription?.text = dishToShow.description

            // Todo: Do better code here. Not Doom Pyramid
            if (dishToShow.allergens != null) {
                for (pos in 0 until dishToShow.allergens.size) {
                    when (dishToShow.allergens[pos]) {
                        1 -> dishAllergensList[pos]?.setImageResource(R.drawable.ic_001)
                        2 -> dishAllergensList[pos]?.setImageResource(R.drawable.ic_002)
                        3 -> dishAllergensList[pos]?.setImageResource(R.drawable.ic_003)
                        4 -> dishAllergensList[pos]?.setImageResource(R.drawable.ic_004)
                    }
                }
            }
        }

        addDishBtn?.setOnClickListener {
            if (dishToShow != null) {
                val notes = dishNotes?.text
                if (notes != null) {
                    val dishToAdd = DishOrdered(dishToShow, notes.toString())
                    onAddDishListener?.onAddDish(dishToAdd)
                }
            }
        }

        cancelBtn?.setOnClickListener {
            onAddDishListener?.dismiss()
        }

        dishNotes?.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
                true
            } else {
                false
            }
        }

        return root
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    private fun commonAttach(listener: Any?) {
        if (listener is OnAddDishListener) {
            onAddDishListener = listener
        }
    }

    override fun onDetach() {
        super.onDetach()
        onAddDishListener = null
    }

    interface OnAddDishListener {
        fun onAddDish(dish: DishOrdered)
        fun dismiss()
    }

}