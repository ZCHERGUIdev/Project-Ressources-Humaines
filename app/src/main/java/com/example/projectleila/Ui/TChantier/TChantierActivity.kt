package com.example.projectleila.Ui.TChantier

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
import com.example.projectleila.Ui.LotssActivity
import kotlinx.android.synthetic.main.activity_tchantier.*

class TChantierActivity : AppCompatActivity() {
    var projectDao: ProjectDao? = null
    var lots=ArrayList<String>()
    var tachs=ArrayList<String>()
    var code=ArrayList<String>()
    var type=ArrayList<String>()
    var phase=ArrayList<String>()
    var HT=ArrayList<String>()
    var HA=ArrayList<String>()
    var cause=ArrayList<String>()
    var date=ArrayList<String>()
    var objIds=ArrayList<String>()
    var listOfProject=ArrayList<Project>()
    var listOfProjectString=ArrayList<String>()
    var adapter: ArrayAdapter<String>?=null
    var  userType:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tchantier)
       userType="tchantier"
        projectDao = ProjectDao()
        supportActionBar!!.hide()
        listOfProjectString!!.add("Finding Project...")
       // Toast.makeText(this, "id"+objIds, Toast.LENGTH_SHORT).show()


        lstProject.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext, LotssActivity::class.java)
            myintet.putExtra("num Lots",lots[i])
            myintet.putExtra("num tach",tachs[i])
            myintet.putExtra("code",code[i])
            myintet.putExtra("type",type[i])
            myintet.putExtra("objId",objIds[i])
            myintet.putExtra("phase",phase[i])
            myintet.putExtra("userType",userType)
            myintet.putExtra("HA",HA[i])
            myintet.putExtra("HT",HT[i])
            myintet.putExtra("date",date[i])

            startActivity(myintet)
        })

        adapter= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfProjectString)
        lstProject.adapter=adapter
        getProject()
    }


    fun getProject(){
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
                    code.add(listoforders[i].COD_MR.toString())
                    type.add(listoforders[i].TYP_RESSOURCE.toString())
                    objIds.add(listoforders[i].objectId.toString())
                    phase.add(listoforders[i].NUM_PHAS.toString())
                    HT.add(listoforders[i].HT.toString())
                    HA.add(listoforders[i].HA.toString())
                    cause.add(listoforders[i].C.toString())
                    date.add(listoforders[i].createdAt.toString())

                }
                adapter?.notifyDataSetChanged()

            }else{
                Toast.makeText(this, "db is empty", Toast.LENGTH_SHORT).show()
            }
        })
    }
}