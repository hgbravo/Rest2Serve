package com.hbravodev.rest2serve.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.fragment.DishDetailFragment
import com.hbravodev.rest2serve.fragment.TableFragment
import com.hbravodev.rest2serve.model.Dish
import com.hbravodev.rest2serve.model.DishOrdered
import com.hbravodev.rest2serve.model.Tables
import kotlinx.android.synthetic.main.activity_table.*

class TableActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        val REQUEST_DISH = 1
        val EXTRA_DISH = "EXTRA_DISH"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TableActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }

    private var tableIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)
        table_number_label.text = getString(R.string.table_title_format, tableIndex + 1)

        if (fragmentManager.findFragmentById(R.id.table_fragment) == null) {
            val fragment = TableFragment.newInstance(tableIndex)
            fragmentManager.beginTransaction()
                    .add(R.id.table_fragment, fragment)
                    .commit()
        }

        add_dish_button.setOnClickListener { v: View? ->
//            startActivity(MenuActivity.intent(this))
            startActivityForResult(MenuActivity.intent(this), REQUEST_DISH)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_DISH) {
            if (resultCode == Activity.RESULT_OK) {
                val dishToAdd = data?.getSerializableExtra(EXTRA_DISH) as? DishOrdered
                if (dishToAdd != null) {
                    Tables[tableIndex].addDish(dishToAdd)
                    val fragment = fragmentManager.findFragmentById(R.id.table_fragment) as? TableFragment
                    fragment?.updateContent()
                }
            }
        }
    }
}
