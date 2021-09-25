package com.example.projectleila.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.projectleila.Dao.ProjectDao
import com.example.projectleila.Poko.Project
import com.example.projectleila.R
import kotlinx.android.synthetic.main.activity_historique.*
import kotlinx.android.synthetic.main.activity_tchantier.*
import kotlinx.android.synthetic.main.info_worker.*
import kotlinx.android.synthetic.main.info_worker.view.*

class HistoriqueActivity : AppCompatActivity() {
    var projectDao: ProjectDao? = null
    var listOfProjectString=ArrayList<String>()
    var adapter: ArrayAdapter<String>?=null
    var code=ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historique)
        supportActionBar!!.hide()
        projectDao = ProjectDao()
        listOfProjectString!!.add("Finding Project...")
        // get project
        adapter= ArrayAdapter(this.baseContext,R.layout.projectviewcell,R.id.txtTip,listOfProjectString)
        lstProjectHisto.adapter=adapter

        lstProjectHisto.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, i, id ->
            var view:View
            view=layoutInflater.inflate(R.layout.info_worker,null)
            view.txtProjectNumber.text="Project "+i
            view.codevalue.text=code[i]
            showAlertDialog(view)
        })
        getProject()
    }
    private fun showAlertDialog(view:View) {
      //  var view:View

        var dialog= AlertDialog.Builder(this)
            .setView(view)
        //view.setBackgroundResource(R.color.tras)
        dialog.create()
        dialog.show()
    }


    fun getProject(){
        projectDao?.getNearbyRecords(Project(),{ listoforders ->
            Log.d("Project size",listoforders.size.toString())

            if (listoforders.size>0)
            {
                txtProjectHisto.text="List De Project ("+listoforders.size+")"

                listOfProjectString.clear()
                for (i in 0..listoforders.size-1)
                {

                    listOfProjectString.add("Project "+ i)
                    code.add(listoforders[i].COD_MR.toString())



                }
                adapter?.notifyDataSetChanged()
                //  progdialog?.hide()

            }
        })
    }
}