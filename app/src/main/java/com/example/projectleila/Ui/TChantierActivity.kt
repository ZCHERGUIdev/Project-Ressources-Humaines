package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.projectleila.Dao.ProjectDao
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_tchantier.*

class TChantierActivity : AppCompatActivity() {
    var projectDao: ProjectDao? = null

    var listOfProject=ArrayList<Project>()
    var listOfProjectString=ArrayList<String>()
    var adapter: ArrayAdapter<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tchantier)
        projectDao = ProjectDao()
        supportActionBar!!.hide()
        listOfProjectString!!.add("Finding Project...")
        lstProject.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext, HMORHAActivity::class.java)
           // myintet.putExtra("username",usernames[i])

            startActivity(myintet)
        })

        adapter= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfProjectString)
        lstProject.adapter=adapter
        getProject()
    }


    fun getProject(){
        //get the near by orders to driver
        projectDao?.getNearbyRecords(Project(),{listoforders ->
            Log.d("Project size",listoforders.size.toString())
            //get pendding+whitoutDrivers
            if (listoforders.size>0)
            {
                txtProject.text="List De Project ("+listoforders.size+")"
                listOfProjectString.clear()
                for (i in 0..listoforders.size-1)
                {

                    listOfProjectString.add("Project "+ i)
                }
                adapter?.notifyDataSetChanged()


            }
        })
    }
}