package com.hbravodev.rest2serve.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.model.Table
import com.hbravodev.rest2serve.model.Tables

class TableListFragment : Fragment() {

    companion object {

        fun newInstance(): TableListFragment = TableListFragment()
    }

    lateinit var root: View
    private var onTableSelectedListener: OnTableSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_table_list, container, false)
            val list = root.findViewById<ListView>(R.id.table_list)
            val adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, Tables.toArray())
            list.adapter = adapter

            // Here we know which table have been selected
            list.setOnItemClickListener { _, _, position, _ ->
                // inform to listener
                onTableSelectedListener?.onTableSelected(Tables[position], position)
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
        if (listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }

    override fun onDetach() {
        super.onDetach()
        onTableSelectedListener = null
    }

    interface OnTableSelectedListener {
        fun onTableSelected(table: Table, position: Int)
    }
}
