package com.example.projectleila.Ui

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
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
    var objIds=ArrayList<String>()
    var id:String?=null
    var listOfProject=ArrayList<Project>()
    var listOfProjectString=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_htactivity)
        //getProject()



        btnSave.setOnClickListener {
            val query = ParseQuery.getQuery<ParseObject>("Project")
            query.whereEqualTo("objectId","3mjCDqaVvu")
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


   /* fun getProject(){
        //get the near by orders to driver
        projectDao?.getNearbyRecords(Project(),{ listoforders ->
            Log.d("Project size",listoforders.size.toString())
            //get pendding+whitoutDrivers
            if (listoforders.size>0)
            {
                txtProject.text="List De Project ("+listoforders.size+")"
                listOfProjectString.clear()
                for (i in 0..listoforders.size-1)
                {
                    listOfProjectString.add("Project "+ i)
                    //objIds.add(listoforders[i].objectId.toString())
                }


            }
        })
    }*/
}