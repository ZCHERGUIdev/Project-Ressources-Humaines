package com.example.projectleila.Ui

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectleila.Dao.ProjectDao
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import com.example.projectleila.Ui.Account.AccountActivity
import com.example.projectleila.Ui.TChantier.TChantierActivity
import com.parse.*
import kotlinx.android.synthetic.main.activity_htactivity.*
import kotlinx.android.synthetic.main.activity_tchantier.*


class HTActivity : AppCompatActivity() {
    var projectDao: ProjectDao? = null
    var listOfProject=ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_htactivity)
        var myintet=getIntent()
        val objId= myintet.getStringExtra("objId")
        val code= myintet.getStringExtra("code")
        val type= myintet.getStringExtra("type")
        val userType= myintet.getStringExtra("userType")
        val HT= myintet.getStringExtra("HT")
        val date= myintet.getStringExtra("date")

        if (userType=="chefProject"){
            //txtHT.text.replace(0,txtHT.textSize.toInt(),HT.toString())
            btnValider.visibility= View.VISIBLE
        }else if(userType=="tchantier"){

        }

        txtCode.text=""+code
        txtType.text=" "+type
        txtDate.text="Date :"+date!!.subSequence(0,20)
        projectDao = ProjectDao()
      //  getProject()
       // Toast.makeText(this, "i'am  "+userType, Toast.LENGTH_LONG).show()

        btnSave.setOnClickListener {
            val query = ParseQuery.getQuery<ParseObject>("Project")
            query.whereEqualTo("objectId",objId)
            query.getFirstInBackground(object : GetCallback<ParseObject> {
                override fun done(`object`: ParseObject?, e: ParseException?) {
                    if (e == null) {

                        val objectID = `object`!!.objectId.toString();
                        query.getInBackground(objectID, object : GetCallback<ParseObject> {
                            override fun done(`object`: ParseObject?, e: ParseException?) {
                                if (e == null) {
                                    if (txtHT.text.isEmpty()){
                                     Toast.makeText(this@HTActivity, "pleaze entre nbr heure", Toast.LENGTH_SHORT).show()
                                    }else{
                                        `object`!!.put("HT", txtHT.text.toString()+" h")
                                        `object`.saveInBackground(object : SaveCallback {
                                            override fun done(e: ParseException?) {
                                                Toast.makeText(this@HTActivity, "done", Toast.LENGTH_SHORT).show()
                                                startActivity(Intent(this@HTActivity,
                                                    TChantierActivity::class.java))
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
        startActivity(Intent(this, AccountActivity::class.java))
    }




}