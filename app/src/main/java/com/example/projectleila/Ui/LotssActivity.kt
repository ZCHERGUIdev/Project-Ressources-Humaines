package com.example.projectleila.Ui

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_lots.*
import kotlinx.android.synthetic.main.activity_tchantier.*

class LotssActivity : AppCompatActivity() {
    var listOfLots=ArrayList<Project>()
    var listOfLotsString=ArrayList<String>()
    var listOfTachString=ArrayList<String>()
    var adapter: ArrayAdapter<String>?=null
    var adapter1: ArrayAdapter<String>?=null
    val ids:ArrayList<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lots)
        supportActionBar!!.hide()
        var myintet=getIntent()
     val lots=  myintet.getStringExtra("num Lots")
     val tach= myintet.getStringExtra("num tach")


        // list des lots
     listOfLotsString.add(lots.toString())
     adapter= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfLotsString)
     lstLots.adapter=adapter
       // list des tach
     listOfTachString.add(tach.toString())
     adapter1= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfTachString)
     lstTach.adapter=adapter1

        lstLots.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext,HMORHAActivity::class.java)
            startActivity(myintet)
        })
        lstTach.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext,HMORHAActivity::class.java)
            startActivity(myintet)
        })


    }
}


