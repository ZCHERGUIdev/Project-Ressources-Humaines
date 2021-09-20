package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_main.*

class UserType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        cproject.setOnClickListener {
           startActivity(Intent(this,HMORHAActivity::class.java))
        }
        respoQali.setOnClickListener {
            startActivity(Intent(this,HMORHAActivity::class.java))
        }
        respo3.setOnClickListener {
            startActivity(Intent(this,TChantierActivity::class.java))        }
    }
}