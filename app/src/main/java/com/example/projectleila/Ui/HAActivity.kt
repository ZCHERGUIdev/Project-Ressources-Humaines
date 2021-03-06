package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projectleila.R
import com.example.projectleila.Ui.Account.AccountActivity
import com.parse.*
import kotlinx.android.synthetic.main.activity_haactivity.*
import kotlinx.android.synthetic.main.activity_htactivity.*
import kotlinx.android.synthetic.main.activity_htactivity.btnSave
import kotlinx.android.synthetic.main.activity_htactivity.txtHT

class HAActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haactivity)
        var myintet=getIntent()
        val objId= myintet.getStringExtra("objId")
        val code= myintet.getStringExtra("code")
        val type= myintet.getStringExtra("type")
        val userType= myintet.getStringExtra("userType")
        val date= myintet.getStringExtra("date")
        val Haret= myintet.getStringExtra("HA")
        val C= myintet.getStringExtra("C")
       // Toast.makeText(this, "user  "+userType, Toast.LENGTH_SHORT).show()

        txtCode2.text=""+code
        txtType2.text=" "+type
        txtDate2.text="Date :"+date!!.subSequence(0,20)
        if (userType=="chefProject"){
            txtHarret.setText(Haret)
            txtCause.setText(C)
            btnValid2.visibility= View.VISIBLE
        }

        btnSave2.setOnClickListener {
            val query = ParseQuery.getQuery<ParseObject>("Project")
            query.whereEqualTo("objectId",objId)
            query.getFirstInBackground(object : GetCallback<ParseObject> {
                override fun done(`object`: ParseObject?, e: ParseException?) {
                    if (e == null) {

                        val objectID = `object`!!.objectId.toString();
                        query.getInBackground(objectID, object : GetCallback<ParseObject> {
                            override fun done(`object`: ParseObject?, e: ParseException?) {
                                if (e == null) {
                                    if (txtHarret.text.isEmpty() ){
                                        Toast.makeText(this@HAActivity, "Entre nbr heure d'arr??t", Toast.LENGTH_SHORT).show()
                                    }else if(txtCause.text.isEmpty()){
                                        Toast.makeText(this@HAActivity, "Veuillez remplir les champs", Toast.LENGTH_SHORT).show()

                                    }else{
                                        `object`!!.put("HA", txtHarret.text.toString()+" ")
                                        `object`!!.put("C", txtCause.text.toString())

                                        `object`.saveInBackground(object : SaveCallback {
                                            override fun done(e: ParseException?) {
                                                Toast.makeText(this@HAActivity, "done", Toast.LENGTH_SHORT).show()
                                                txtHarret.text.clear()
                                                txtCause.text.clear()
                                            }

                                        })
                                    }

                                }
                            }

                        })

                    }
                }

            })
        }
    }
    fun gotoHome(view: View){
        startActivity(Intent(this,HMORHAActivity::class.java))
    }
    fun logOut(view: View){
        ParseUser.logOut()
        startActivity(Intent(this,AccountActivity::class.java))
    }
}