package com.example.projectleila

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportActionBar!!.hide()
        cproject.setOnClickListener {
            Toast.makeText(this, "chef de projectt run activity", Toast.LENGTH_SHORT).show()
        }
        respoQali.setOnClickListener {
            Toast.makeText(this, "responssable de qualité run activity", Toast.LENGTH_SHORT).show()
        }
        respo3.setOnClickListener {
            Toast.makeText(this, " run activity", Toast.LENGTH_SHORT).show()
        }
    }
}