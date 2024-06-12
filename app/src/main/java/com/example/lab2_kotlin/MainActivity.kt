package com.example.lab2_kotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val CALL_PHONE_PERMISSION_CHECK = 4321
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun doActivity(v: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setClass(this, SecondActivity::class.java)
        startActivity(intent)
    }

    fun doYahoo(v: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://www.yahoo.com.tw")
        startActivity(intent)
    }

    fun doPhone(v: View) {
        checkPermission()
    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this,"need to set permission", Toast.LENGTH_SHORT).show()
        } else {
            realCallPhone()
        }
    }

    private fun realCallPhone() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:1234565")
        startActivity(intent)
    }

}