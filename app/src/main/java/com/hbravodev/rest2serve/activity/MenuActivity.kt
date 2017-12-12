package com.hbravodev.rest2serve.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hbravodev.rest2serve.R

class MenuActivity : AppCompatActivity() {

    companion object {

        fun intent(context: Context) : Intent = Intent(context, MenuActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}
