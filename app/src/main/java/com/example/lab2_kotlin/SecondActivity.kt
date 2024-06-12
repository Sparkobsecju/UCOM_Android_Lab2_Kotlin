package com.example.lab2_kotlin

import android.graphics.Color
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
        val button4: Button = findViewById(R.id.button4)
        button4.setOnClickListener(Bar(this))
        val button5: Button = findViewById(R.id.button5)
        button5.setOnClickListener { v ->
            Toast.makeText(
                this, "button 5 clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
        val button6: Button = findViewById(R.id.button6)
        button6.setOnClickListener{
            Toast.makeText(this, "Button 6 clicked",
                Toast.LENGTH_SHORT).show()
        }
        val button7: Button = findViewById(R.id.button7)
        button7.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                Toast.makeText(this@SecondActivity,
                    "Button 7 clicked", Toast.LENGTH_SHORT).show()
            }
        })
        val button8: Button = findViewById(R.id.button8)
        button8.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                v!!.setBackgroundColor(Color.BLUE)
            }
        })
        val button9: Button = findViewById(R.id.button9)
        button9.setOnClickListener{ v ->
            val button: Button = v as Button
            button.text = "1234567"
            button.setBackgroundColor(Color.BLACK)
        }

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