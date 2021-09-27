package com.example.projectleila.Dao

import android.util.Log
import com.example.projectleila.Poko.Project
import com.example.projectleila.Ui.Account.AccountActivity
import com.parse.*

class ProjectDao {
    var userdao:UserDao?=null
    constructor() {
        var userDao=UserDao()
        var obj = ParseObject(Project::class.java.simpleName)
        var defaultAcl=ParseACL()
        defaultAcl.publicReadAccess=true
        defaultAcl.publicWriteAccess=true
        obj.acl=defaultAcl
    }
    /*
       var objectId:String?=null
    var customerUsername:String?=null
    var driverUsername:String?=null
    var cistomerLocation:ParseGeoPoint?=null // Project
    var driverLocation:ParseGeoPoint?=null // Driver(User)
    var ProjectStatus:String?=null // status -> Location
    */
/*
    // Create Record
    fun creatrecord(rec: Project) {

        var obj = ParseObject(Project::class.java.simpleName)
        obj.put("customerUsername", rec.customerUsername)
        obj.put("cistomerLocation", rec.customerLocation)
        obj.put("status", ProjectStatus.pendding.toString())
        obj.saveInBackground(object : SaveCallback {
            override fun done(e: ParseException?) {
                if (e == null) {
                    Log.i("app", "record is saved")
                } else {
                    Log.e("App", "Record is not saved" + e.printStackTrace())
                }
            }
        })
    }
    // update record
    fun updaterecord(rec: Project) {

        var query = ParseQuery<ParseObject>(Project::class.java.simpleName)
        query.getInBackground(rec.objectId, { obj, e ->
            if (e==null) {

                obj.put("customerUsername", rec.customerUsername)
                obj.put("customerLocation", rec.customerLocation)
                obj.put("status", rec.ProjectStatus)
                obj.saveInBackground {
                    userdao?.updateUserLocation(rec.customerLocation)
                }
            }else{
                creatrecord(rec)
            }
        })
    }
    // Read REcorde
    fun getRecordByCustomerUserName(rec: Project):Project {
        var record=Project()
        var query = ParseQuery.getQuery<ParseObject>(Project::class.java.simpleName)
        query.whereEqualTo("customerUsername",rec.customerUsername)
        query.getFirstInBackground({obj,e->
            if (e==null) {
                Log.i("APP",obj.getString("description"))
                record =toLocalrecord(obj)
            }else{
                Log.i("APP","description"+e.message)
            }
        })

        return record
    }
    private fun toLocalrecord(parseProject: ParseObject?): Project {
        var Project=Project()
        Project.objectId=parseProject!!.objectId
        Project.customerUsername=parseProject.getString("customerUsername")
        Project.customerLocation=parseProject.getParseGeoPoint("customerLocation")
        Project.ProjectStatus=parseProject.getString("status")
        return Project
    }

    private fun toLocalRecordDeleted(parseProject: ParseObject?){
        var Project=Project()
        Project.objectId=parseProject!!.objectId
        Project.customerUsername=parseProject.getString("customerUsername")
        Project.customerLocation=parseProject.getParseGeoPoint("customerLocation")
        Project.ProjectStatus=parseProject.getString("status")
    }
    fun deleterecord(rec: Project) {


        //get and delete
        var query = ParseQuery<ParseObject>(Project::class.java.simpleName)
        query.getInBackground(rec.objectId, { obj, e ->
            if (e==null) {
                toLocalRecordDeleted(obj)
                obj.deleteInBackground()
            }else{
                var po=ParseObject(Project::class.java.simpleName)
                po.put("title","not found")
                toLocalRecordDeleted(po)
            }
        })
    }
    fun getRecords(rec: Project){
        var query = ParseQuery.getQuery<ParseObject>(Project::class.java.simpleName)
        query.whereEqualTo("customerUsername",rec.customerUsername)
        query.findInBackground ({ objs, e ->
            if (e==null){
                var item= mutableListOf<Project>()
                for (i in 0..item.size)
                {
                    Log.i("APP",item[i].customerUsername.toString())
                }

            }else
            {
                Log.e("APP",e.message)
            }
        })


    }
    fun creatrecordWithCalback(rec: Project, callback:(returnedProject:Project)->Unit){
        var obj = ParseObject(Project::class.java.simpleName)
        obj.put("customerUsername",rec.customerUsername)
        obj.put("customerLocation", rec.customerLocation)
        obj.put("status", rec.ProjectStatus)
        obj.saveInBackground(object : SaveCallback {
            override fun done(e: ParseException?) {
                if (e == null) {
                    Log.i("app", "record is saved")
                    var o2=toSingleRecord(obj)
                    callback(o2)
                } else {
                    Log.e("App", "Record is not saved" + e.printStackTrace())
                    callback(Project())
                }
            }
        })
    }

    fun getPendingRecordsByCustomerUserName(Project: Project, callback:(listOfRecord:MutableList<Project>)->Unit) {
        var query = ParseQuery.getQuery<ParseObject>(Project::class.java.simpleName)
        query.whereEqualTo("status",Project.ProjectStatus)
        query.whereEqualTo("customerUsername",Project.customerUsername)

        query.findInBackground ({ objs, e ->
            if (e == null) {
                var item = mutableListOf<Project>()
                objs.mapTo(item){toSingleRecord(it)}
                callback(item)
            } else {
                Log.e("APP",e.message)
            }
        })

    }

    fun getRecordsByCustomerUserName(Project: Project, callback:(listOfRecord:MutableList<Project>)->Unit) {
        var query = ParseQuery.getQuery<ParseObject>(Project::class.java.simpleName)
        query.whereEqualTo("status",Project.ProjectStatus)
        query.whereEqualTo("customerUsername",Project.customerUsername)

        query.findInBackground ({ objs, e ->
            if (e == null) {
                var item = mutableListOf<Project>()
                objs.mapTo(item){toSingleRecord(it)}
                callback(item)
            } else {
                Log.e("APP",e.message)
            }
        })

    }



    fun cancelrecordsWithCallback(listofProjects: MutableList<Project>, callback:(isUpdated:Boolean)->Unit) {
        for (i in 0..listofProjects.size-1) {
            listofProjects[i].ProjectStatus = ProjectStatus.cancel.toString()
            updaterecord( listofProjects[i])
        }
        callback(true)
    }



    fun updaterecordWithAllDetailsWithCalback(ProjectStarter: Project, callback:(retrnedProject:Project)->Unit ) {

        //get all pendding Projects for the given username
        var query1 = ParseQuery.getQuery<ParseObject>(Project::class.java.simpleName)
        query1.whereEqualTo("customerUsername",ProjectStarter.customerUsername)
        if (ProjectStarter.ProjectStatus==ProjectStatus.pendding.toString())
        { query1.whereEqualTo("status",ProjectStatus.inprogress.toString()) }
        if (ProjectStarter.ProjectStatus==ProjectStatus.completed.toString())
        {
            query1.whereEqualTo("status",ProjectStatus.inprogress.toString())
            query1.whereEqualTo("driverUsername",ProjectStarter.driverUsername)
        }
        query1.ProjectByDescending("createdAt")
        query1.findInBackground ({ objs, e ->
            if (e == null) {
                var item = mutableListOf<Project>()
                objs.mapTo(item){toSingleRecord(it)}
                var obj=item[0]
                //get and save
                var query = ParseQuery<ParseObject>(Project::class.java.simpleName)
                query.getInBackground(obj.objectId, { ProjectParseObject, e ->
                    if (e==null) {
                        ProjectParseObject.put("customerUsername", ProjectStarter.customerUsername)
                        ProjectParseObject.put("driverUsername",ProjectStarter.driverUsername)
                        ProjectParseObject.put("customerLocation", ProjectStarter.customerLocation)
                        ProjectParseObject.put("status", ProjectStarter.ProjectStatus)
                        ProjectParseObject.saveInBackground {
                            obj.ProjectStatus= ProjectStarter.ProjectStatus
                            callback(obj)
                            // write your code after save
                            // userdao?.updateUserLocation(obj.customerLocation)
                        }
                    }
                })



            } else {
                Log.e("APP",e.message)
            }
        })

    }*/
    private fun toSingleRecord(parseProject: ParseObject?): Project {
        var Project=Project()
        Project.objectId=parseProject!!.objectId
        Project.COD_ENTREPRISE=parseProject.getInt("COD_ENTREPRISE")
        Project.COD_MR=parseProject.getInt("COD_MR")
        Project.BGINT=parseProject.getString("BGINT")
        Project.NUM_LOT=parseProject.getInt("NUM_LOT")
        Project.INT_LOT=parseProject.getString("INT_LOT")
        Project.NUM_PHAS=parseProject.getInt("NUM_PHAS")
        Project.DES_PHAS=parseProject.getString("DES_PHAS")
        Project.NUM_TACHE=parseProject.getInt("NUM_TACHE")
        Project.DES_TACHE=parseProject.getString("DES_TACHE")
        Project.TYP_RESSOURCE=parseProject.getString("TYP_RESSOURCE")
        Project.COD_RSRC=parseProject.getString("COD_RSRC")
        Project.LIB_RSRC=parseProject.getString("LIB_RSRC")
        Project.HT=parseProject.getString("HT")
        Project.HA=parseProject.getString("HA")
        Project.C=parseProject.getString("C")

        return Project
    }


    fun getNearbyRecords(Project: Project, callback:(listofRecords:MutableList<Project>)->Unit) {

        var query = ParseQuery.getQuery<ParseObject>(Project::class.java.simpleName)
       // query.whereEqualTo("status",ProjectStatus.pendding.toString())
      //  query.whereNear("customerLocation",Project.driverLocation)
        query.setLimit(100)
        query.findInBackground ({ objs, e ->
            if (e == null) {
                var item = mutableListOf<Project>()
                objs.mapTo(item){toSingleRecord(it)}
                callback(item)
            } else {
                Log.e("APP",e.message!!)
            }
        })

    }
}

