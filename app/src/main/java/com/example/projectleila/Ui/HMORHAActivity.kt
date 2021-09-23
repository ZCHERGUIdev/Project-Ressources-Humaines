package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_hmorhaactivity.*

class HMORHAActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hmorhaactivity)
        supportActionBar!!.hide()
        var myintet=getIntent()
        val objId1= myintet.getStringExtra("objId")
        val code= myintet.getStringExtra("code")
        val type= myintet.getStringExtra("type")
        Toast.makeText(this, "id "+objId1, Toast.LENGTH_SHORT).show()
        HM.setOnClickListener {
            var myintet =Intent(this,HTActivity::class.java)
            myintet.putExtra("objId",objId1)
            myintet.putExtra("code",code)
            myintet.putExtra("type",type)
            startActivity(myintet)
        }
        HA.setOnClickListener {
            var myintet =Intent(this,HAActivity::class.java)
            startActivity(myintet)
        }

    }



}