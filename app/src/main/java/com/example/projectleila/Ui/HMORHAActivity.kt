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
        val userType= myintet.getStringExtra("userType")
        val HT= myintet.getStringExtra("HT")
        val date= myintet.getStringExtra("date")

       // Toast.makeText(this, "user  "+userType, Toast.LENGTH_SHORT).show()
        HM.setOnClickListener {
            var myintet =Intent(this,HTActivity::class.java)
            myintet.putExtra("objId",objId1)
            myintet.putExtra("code",code)
            myintet.putExtra("type",type)
            myintet.putExtra("userType",userType)
            myintet.putExtra("HT",HT)
            myintet.putExtra("date",date)
            startActivity(myintet)
        }
        HA.setOnClickListener {
            var myintet =Intent(this,HAActivity::class.java)
            myintet.putExtra("objId",objId1)
            myintet.putExtra("code",code)
            myintet.putExtra("type",type)
            myintet.putExtra("userType",userType)
            myintet.putExtra("date",date)
            startActivity(myintet)
        }

    }


    fun gotoHome(view: View){
        startActivity(Intent(this,LotssActivity::class.java))
    }
    fun gotoHisto(view: View){
        startActivity(Intent(this,HistoriqueActivity::class.java))
    }




}