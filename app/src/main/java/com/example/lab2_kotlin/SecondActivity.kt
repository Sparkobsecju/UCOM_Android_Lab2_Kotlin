package com.example.lab2_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener(this)
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener(this)
        val button3: Button = findViewById(R.id.button3)
        button3.setOnClickListener(Foo())
    }

    inner class Foo : View.OnClickListener {
        override fun onClick(v: View?) {
            Toast.makeText(this@SecondActivity,
                "Button 3 clicked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View?) {
        var message = ""
        when( v!!.id) {
            R.id.button1 -> message = "1"
            R.id.button2 -> message = "2"
        }
        Toast.makeText(this, "Button ${message} clicked", Toast.LENGTH_SHORT).show()
    }
}