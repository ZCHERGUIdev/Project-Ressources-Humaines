package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_hmorhaactivity.*

class HMORHAActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hmorhaactivity)
        supportActionBar!!.hide()

        HM.setOnClickListener {
            var myintet =Intent(this,HTActivity::class.java)
            startActivity(myintet)
        }
        HA.setOnClickListener {
            var myintet =Intent(this,HAActivity::class.java)
            startActivity(myintet)
        }

    }



}