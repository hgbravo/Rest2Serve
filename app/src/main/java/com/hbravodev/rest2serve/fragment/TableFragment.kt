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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

//        val tempDish = Dish("Paella", arrayOf("Mariscos"), 10.9f, "Uno de los mejores platos")
//        Tables[tableIndex].addDish(tempDish)
//        Log.v("TAG", "${Tables[tableIndex].number}")

        // Inflate the layout for this fragment
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table, container, false)
            val list = root.findViewById<ListView>(R.id.dishes_list)
            val adapter = ArrayAdapter<Dish>(activity, android.R.layout.simple_list_item_1, Tables[tableIndex].dishes())
            list.adapter = adapter
        }

        return root
    }
}
