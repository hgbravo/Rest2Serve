package com.hbravodev.rest2serve.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ViewSwitcher
import com.hbravodev.rest2serve.R
import com.hbravodev.rest2serve.adapter.MenuRecyclerViewAdapter
import com.hbravodev.rest2serve.model.Dish
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*

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
                // 4. Assign adapter to RecyclerView
                menuList.adapter = MenuRecyclerViewAdapter(value)
                viewSwitcher.displayedChild = VIEW_INDEX.MENU.index
            }
            else {

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

            // 3. Set itemAnimator
            menuList.itemAnimator = DefaultItemAnimator()

            if (menu == null) {
                updateMenu()
            }


        }

        return root
    }

    private fun updateMenu() {

        viewSwitcher.displayedChild = VIEW_INDEX.LOADING.index

        async(UI) {
            val newMenu: Deferred<List<Dish>?> = bg {
                downloadMenu()
            }

            val downloadedMenu = newMenu.await()
            if (downloadedMenu !=  null) {
                menu = downloadedMenu
            } else {
                AlertDialog.Builder(activity)
                        .setTitle("Error")
                        .setMessage("The menu info could not be downloaded.")
                        .setPositiveButton("Retry", { dialog, _ ->
                            dialog.dismiss()
                            updateMenu()
                        })
                        .setNegativeButton("Cancel", { _, _ -> activity.finish() })
                        .show()
            }
        }
    }

    private fun downloadMenu(): List<Dish>? {
        try {
            val url = URL("http://www.mocky.io/v2/5a318c4c2e00006e35e3b554")
            val jsonString = Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next()

            val jsonRoot = JSONObject(jsonString)
            val list = jsonRoot.getJSONArray("Dishes")

            val dishes = mutableListOf<Dish>()

            for (dishIndex in 0 until list.length()) {
                val dish = list.getJSONObject(dishIndex)
                val name = dish.getString("name")
                val allergens = dish.getJSONArray("allergens") as? IntArray
                val price = dish.getDouble("price").toFloat()
                val description = dish.getString("description")
                val image = dish.getInt("image")

                dishes.add(Dish(name, allergens, price, description, image))
            }

            return dishes
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    public override fun onAttach(context:Context?) {
        super.onAttach(context)

    }

    public override fun onDetach() {
        super.onDetach()

    }

}
