package com.example.lab2_kotlin

import android.view.View
import android.widget.Toast

class Bar(private val context: SecondActivity) : View.OnClickListener {
    override fun onClick(v: View?) {
        Toast.makeText(
            context,
            "Button 4 clicked", Toast.LENGTH_SHORT
        ).show()
    }
}
