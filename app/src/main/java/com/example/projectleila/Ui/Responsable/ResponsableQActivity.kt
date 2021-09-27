package com.example.projectleila.Ui.Responsable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.projectleila.Dao.ProjectDao
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import com.example.projectleila.Ui.HistoriqueActivity
import com.example.projectleila.Ui.LotssActivity
import kotlinx.android.synthetic.main.activity_tchantier.*

class ResponsableQActivity : AppCompatActivity() {
    var projectDao: ProjectDao? = null
    var lots=ArrayList<String>()
    var tachs=ArrayList<String>()
    var listOfProject=ArrayList<Project>()
    var listOfProjectString=ArrayList<String>()
    var adapter: ArrayAdapter<String>?=null
    var  userType:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_responsable_qactivity)
        supportActionBar!!.hide()
        projectDao = ProjectDao()
     /*   val  userType="Responsable"
        listOfProjectString!!.add("Finding Project...")
        lstProject.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext, LotssActivity::class.java)
            myintet.putExtra("num Lots",lots[i])
            myintet.putExtra("num tach",tachs[i])
            myintet.putExtra("userType",userType)

            startActivity(myintet)
        })*/

        adapter= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfProjectString)
        lstProject.adapter=adapter
        getProject()
        listOfProjectString!!.add("Finding Project...")
        lstProject.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var myintet = Intent(this.baseContext, HistoriqueActivity::class.java)
            startActivity(myintet)
        })
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
                }
                adapter?.notifyDataSetChanged()
                //  progdialog?.hide()

            }
        })
    }
}