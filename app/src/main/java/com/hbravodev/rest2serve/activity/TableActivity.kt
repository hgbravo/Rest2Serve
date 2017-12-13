package com.hbravodev.rest2serve.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.fragment.TableFragment
import kotlinx.android.synthetic.main.activity_table.*

class TableActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, TableActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        val tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0)
        table_number_label.text = getString(R.string.table_title_format, tableIndex + 1)

        if (fragmentManager.findFragmentById(R.id.table_fragment) == null) {
            val fragment = TableFragment.newInstance(tableIndex)
            fragmentManager.beginTransaction()
                    .add(R.id.table_fragment, fragment)
                    .commit()
        }

        add_dish_button.setOnClickListener { v: View? ->
            startActivity(MenuActivity.intent(this))
        }
    }
}
