package com.hbravodev.rest2serve.activity

import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.fragment.TableFragment
import com.hbravodev.rest2serve.fragment.TableListFragment
import com.hbravodev.rest2serve.model.Table

class MainActivity : AppCompatActivity(), TableListFragment.OnTableSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (findViewById<View>(R.id.table_list_fragment) != null) {
            // First, check if we don't have the fragment in our view hierarchy
            if (fragmentManager.findFragmentById(R.id.table_list_fragment) == null) {
                val fragment = TableListFragment.newInstance()
                fragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, fragment)
                        .commit()
            }
        }
    }

    override fun onTableSelected(table: Table, position: Int) {
        val tableFragment = fragmentManager.findFragmentById(R.id.fragment_table) as? TableFragment
        if (tableFragment == null) {
            startActivity(TableActivity.intent(this, position))
        }
    }
}
