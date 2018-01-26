package com.hbravodev.rest2serve.fragment

import android.os.Bundle
import android.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.model.Dish
import com.hbravodev.rest2serve.model.DishOrdered
import com.hbravodev.rest2serve.model.Tables
import kotlinx.android.synthetic.main.dish_ordered_list_row.view.*
import java.util.ArrayList
import java.util.zip.Inflater

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
    private var orderedDishes = ArrayList<DishOrdered>()
    var list: ListView? = null
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        if (inflater != null) {
            tableIndex = arguments.getInt(ARG_TABLE_INDEX)
            orderedDishes = Tables[tableIndex].items as ArrayList<DishOrdered>
            root = inflater.inflate(R.layout.fragment_table, container, false)
            list = root.findViewById(R.id.dishes_list)
            adapter = MyListAdapter()
            list?.adapter = adapter
        }

        return root
    }

    override fun onResume() {
        super.onResume()
    }

    public fun updateContent() {
        adapter = MyListAdapter()
        list?.adapter = adapter
    }

    private inner class MyListAdapter(): BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val dishRow: View

            if (convertView == null) {
                val inflater = LayoutInflater.from(parent?.context)
                dishRow = inflater.inflate(R.layout.dish_ordered_list_row, parent, false)

                val viewHolder = ViewHolder(dishRow.dish_textview, dishRow.dish_notes_textview)
                dishRow.tag = viewHolder
            } else{
                dishRow = convertView
            }

            val viewHolder = dishRow.tag as ViewHolder

            viewHolder.dishTextView.text = orderedDishes[position].toString()
            viewHolder.dishNotesTextView.text = orderedDishes[position].notes

            return dishRow
        }

        override fun getItem(position: Int): Any {
            return orderedDishes[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return orderedDishes.count()
        }
    }

    private class ViewHolder(val dishTextView: TextView, val dishNotesTextView: TextView)
}
