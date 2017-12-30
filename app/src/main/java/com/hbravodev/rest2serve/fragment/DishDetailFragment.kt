package com.hbravodev.rest2serve.fragment

import android.app.Fragment
import android.os.Bundle
import com.hbravodev.rest2serve.model.Dish

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


}