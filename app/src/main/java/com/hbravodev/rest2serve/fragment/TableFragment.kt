package com.hbravodev.rest2serve.fragment

import android.os.Bundle
import android.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.model.Dish
import com.hbravodev.rest2serve.model.Tables
import java.util.ArrayList

class TableFragment : Fragment() {

    companion object {
        private val ARG_TABLE_INDEX = "ARG_TABLE_INDEX"

        fun newInstance(tableIndex: Int): TableFragment {
            val fragment = TableFragment()
            val args = Bundle()
            args.putInt(ARG_TABLE_INDEX, tableIndex)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var root: View
    private var tableIndex  = 0
    var list: ListView? = null
    private lateinit var adapter: ArrayAdapter<Dish>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table, container, false)
            list = root.findViewById(R.id.dishes_list)
            adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, Tables[tableIndex].dishes())
            list?.adapter = adapter
        }

        return root
    }

    override fun onResume() {
        super.onResume()
    }

    public fun updateContent() {
        adapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1, Tables[tableIndex].dishes())
        list?.adapter = adapter
    }
}
