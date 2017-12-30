package com.hbravodev.rest2serve.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.fragment.MenuFragment
import com.hbravodev.rest2serve.model.Dish

class MenuActivity : AppCompatActivity(), MenuFragment.OnDishSelectedListener {

    companion object {

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
                        .commit()
            }
        }
    }

    override fun onDishSelected(dish: Dish?) {
        // Launch Detail Fragment Todo
    }
}
