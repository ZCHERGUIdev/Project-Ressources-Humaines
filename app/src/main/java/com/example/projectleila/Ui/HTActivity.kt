package com.example.projectleila.Ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projectleila.Dao.ProjectDao
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import com.parse.*
import kotlinx.android.synthetic.main.activity_htactivity.*
import kotlinx.android.synthetic.main.activity_tchantier.*


class HTActivity : AppCompatActivity() {
    var projectDao: ProjectDao? = null
    var listOfProject=ArrayList<Project>()
    var listOfProjectString=ArrayList<String>()

    var tachs=ArrayList<String>()
    var code=ArrayList<String>()
    var type=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_htactivity)
        var myintet=getIntent()
        val objId= myintet.getStringExtra("objId")
        val code= myintet.getStringExtra("code")
        val type= myintet.getStringExtra("type")

        txtCode.text=""+code
        txtType.text=" "+type
        projectDao = ProjectDao()
        getProject()
        Toast.makeText(this, "id "+objId, Toast.LENGTH_LONG).show()

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

                                    `object`!!.put("HT", txtHT.text.toString())
                                    `object`.saveInBackground(object : SaveCallback {
                                        override fun done(e: ParseException?) {
                                            Toast.makeText(this@HTActivity, "done", Toast.LENGTH_SHORT).show()
                                        }

                                    })
                                }
                            }

                        })

                    }
                }

            })
        }

    }


    fun getProject(){
        //get the near by project
        projectDao?.getNearbyRecords(Project(),{ listofprojects ->
            Log.d("Project size",listofprojects.size.toString())
            //get pendding+whitoutDrivers
            if (listofprojects.size>0)
            {
                //txtHT.text="List De Project ("+listofprojects.size+")"
                listOfProjectString.clear()
                for (i in 0..listofprojects.size-1)
                {
                    listOfProjectString.add("Project "+ i)

                }


            }
        })
    }
}