package com.hbravodev.rest2serve.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.fragment.DishDetailFragment
import com.hbravodev.rest2serve.fragment.MenuFragment
import com.hbravodev.rest2serve.model.Dish
import com.hbravodev.rest2serve.model.DishOrdered

class MenuActivity : AppCompatActivity(), MenuFragment.OnDishSelectedListener, DishDetailFragment.OnAddDishListener {

    companion object {
        private val EXTRA_DISH = "EXTRA_DISH"

        fun intent(context: Context) : Intent = Intent(context, MenuActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        if (findViewById<View>(R.id.menu_fragment) != null) {
            if (fragmentManager.findFragmentById(R.id.menu_fragment) == null) {
                val fragment = MenuFragment.newInstance()
                fragmentManager.beginTransaction()
                        .add(R.id.menu_fragment, fragment)
                        .addToBackStack("stack0")
                        .commit()
            }
        }
    }

    override fun onDishSelected(dish: Dish) {
        if (findViewById<View>(R.id.menu_fragment) != null) {
                val fragment = DishDetailFragment.newInstance(dish)
                fragmentManager.beginTransaction()
                        .add(R.id.menu_fragment, fragment)
                        .addToBackStack("stack1")
                        .commit()
        }
    }

    override fun onAddDish(dish: DishOrdered) {
        val returnIntent = Intent()
        returnIntent.putExtra(EXTRA_DISH, dish)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun dismiss() {
//        super.onBackPressed()
        fragmentManager.popBackStack("stack0", 0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
