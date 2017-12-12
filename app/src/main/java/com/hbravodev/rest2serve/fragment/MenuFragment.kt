package com.hbravodev.rest2serve.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewSwitcher

import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.model.Dish

class MenuFragment:Fragment() {

    enum class VIEW_INDEX(val index: Int) {
        LOADING(0),
        MENU(1)
    }

    companion object {
        fun newInstance() : MenuFragment = MenuFragment()
    }

    lateinit var root: View
    lateinit var viewSwitcher: ViewSwitcher
    lateinit var menuList: RecyclerView

    var menu: List<Dish>? = null
        set(value) {
            field = value
            if (value != null) {

            }
        }

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater:LayoutInflater?, container:ViewGroup?,
                              savedInstanceState:Bundle?):View? {
        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_menu, container, false)

            viewSwitcher = root.findViewById(R.id.view_switcher)
            viewSwitcher.setInAnimation(activity, android.R.anim.fade_in)
            viewSwitcher.setOutAnimation(activity, android.R.anim.fade_out)

            // 1. Access to RecyclerView
            menuList = root.findViewById(R.id.menu_list)

            // 2. Set the LayoutManager
            menuList.layoutManager = GridLayoutManager(activity, 1)

        }

    }

    public override fun onAttach(context:Context?) {
        super.onAttach(context)

    }

    public override fun onDetach() {
        super.onDetach()

    }

}
