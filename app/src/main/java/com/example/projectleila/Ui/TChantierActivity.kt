package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.projectleila.Dao.ProjectDao
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_tchantier.*

class TChantierActivity : AppCompatActivity() {
    var projectDao: ProjectDao? = null
    var lots=ArrayList<String>()
    var tachs=ArrayList<String>()
    var objIds=ArrayList<String>()
    var listOfProject=ArrayList<Project>()
    var listOfProjectString=ArrayList<String>()
    var adapter: ArrayAdapter<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tchantier)
        projectDao = ProjectDao()
        supportActionBar!!.hide()
        listOfProjectString!!.add("Finding Project...")
       // Toast.makeText(this, "id"+objIds, Toast.LENGTH_SHORT).show()


        lstProject.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext, LotssActivity::class.java)
            myintet.putExtra("num Lots",lots[i])
            myintet.putExtra("num tach",tachs[i])
            myintet.putExtra("objId",objIds[i])
            //Toast.makeText(this, "id"+objIds[i], Toast.LENGTH_SHORT).show()
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
                    lots.add(listoforders[i].NUM_LOT.toString())
                    tachs.add(listoforders[i].NUM_TACHE.toString())
                    objIds.add(listoforders[i].objectId.toString())
                }
                adapter?.notifyDataSetChanged()

            }
        })
    }
}