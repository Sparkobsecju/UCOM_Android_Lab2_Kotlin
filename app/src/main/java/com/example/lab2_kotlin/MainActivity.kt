package com.example.lab2_kotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object {
        private const val CALL_PHONE_PERMISSION_CHECK = 1314
        private const val TAG = "MAIN_ACTIVITY"
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
        val sb = StringBuilder()
        val dayOfWeek = resources.getStringArray(R.array.day_of_week)
        for (d in dayOfWeek) {
            Log.v(TAG, d)
            sb.append(d + ",")
        }
        val tv1 = findViewById<TextView>(R.id.textView)
        tv1.text = sb.toString()
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
        checkPhonePermission()
    }

    private fun checkPhonePermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CALL_PHONE
                )
            ) {
                showDialog()
            } else {
                askForPermission()
            }
        } else {
            realCallPhone()
        }
    }

    private fun realCallPhone() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:1234565")
        startActivity(intent)
    }

    private fun showDialog() {
        AlertDialog.Builder(this)
            .setTitle("Need a phone call?")
            .setMessage("需要賦予權限，是否同意?")
            .setPositiveButton("OK", { _, _ -> askForPermission() })
            .setNeutralButton("No") { _, _ -> finish() }
            .show()
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CALL_PHONE),
            CALL_PHONE_PERMISSION_CHECK
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            realCallPhone()
        } else {
            val textView: TextView = findViewById(R.id.textView)
            textView.text = "權限需要去設定reset, 同意才能繼續"
        }
    }


}