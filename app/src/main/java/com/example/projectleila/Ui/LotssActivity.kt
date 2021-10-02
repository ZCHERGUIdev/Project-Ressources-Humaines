package com.example.projectleila.Ui

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_lots.*
import kotlinx.android.synthetic.main.activity_tchantier.*

class LotssActivity : AppCompatActivity() {
    var listOfLots=ArrayList<Project>()
    var listOfLotsString=ArrayList<String>()
    var listOfTachString=ArrayList<String>()
    var listOfPhaseString=ArrayList<String>()
    var adapter: ArrayAdapter<String>?=null
    var adapter1: ArrayAdapter<String>?=null
    var adapter2: ArrayAdapter<String>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lots)
        supportActionBar!!.hide()
        var myintet=getIntent()
     val lots=  myintet.getStringExtra("num Lots")
     val tach= myintet.getStringExtra("num tach")
     val code= myintet.getStringExtra("code")
     val type= myintet.getStringExtra("type")
     val objId= myintet.getStringExtra("objId")
     val phase= myintet.getStringExtra("phase")
     val userType= myintet.getStringExtra("userType")
     val HT= myintet.getStringExtra("HT")
     val HA= myintet.getStringExtra("HA")
     val C= myintet.getStringExtra("C")
     val date= myintet.getStringExtra("date")

     Toast.makeText(this, "HT  "+C, Toast.LENGTH_SHORT).show()


        txtNumLot.text="List De Lots (1)"
        txtNumTach.text="List De Tach (1)"
        txtNumPhase.text="List De Phase (1)"
        // list des lots
     listOfLotsString.add(lots.toString())
     adapter= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfLotsString)
     lstLots.adapter=adapter
       // list des tach
     listOfTachString.add(tach.toString())
     adapter1= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfTachString)
     lstTach.adapter=adapter1
        // list des Phase
     listOfPhaseString.add(phase.toString())
     adapter2= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfPhaseString)
     lstPhase.adapter=adapter2

        lstLots.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext,HMORHAActivity::class.java)
            myintet.putExtra("objId",objId)
            myintet.putExtra("code",code)
            myintet.putExtra("type",type)
            myintet.putExtra("userType",userType)
            myintet.putExtra("HT",HT)
            myintet.putExtra("HA",HA)
            myintet.putExtra("date",date)
            myintet.putExtra("C",C)

            startActivity(myintet)
        })
        lstTach.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext,HMORHAActivity::class.java)
            myintet.putExtra("objId",objId)
            myintet.putExtra("code",code)
            myintet.putExtra("type",type)
            myintet.putExtra("userType",userType)
            myintet.putExtra("date",date)

            startActivity(myintet)
        })


    }
}


