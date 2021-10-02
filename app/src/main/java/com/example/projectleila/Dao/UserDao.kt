package com.example.projectleila.Dao

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import com.example.projectleila.Poko.User
import com.example.projectleila.Ui.Account.AccountActivity
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseUser
import com.parse.SignUpCallback
import kotlin.reflect.KParameter

class UserDao {

    var parseuser:ParseUser?=null



    constructor() {
        parseuser= ParseUser()

    }

    //sign up=regester=new account
    //sign in=login
    //sign out
    //update
    fun LogIn(user:User){
        ParseUser.logInInBackground(user.userName,user.password,
            {pUser,ex->
                if (pUser!=null){
                    Log.i("singin","sign in succesfully")
                }else{
                    Log.i("signin","Failed"+ex.printStackTrace())
                }
            })
    }
    public fun SignUp(user: User){
        parseuser?.setPassword(user.password)
        parseuser?.username=user.userName
        parseuser?.email=user.userEmail
        parseuser?.signUpInBackground(object : SignUpCallback {
            override fun done(e: ParseException?) {
                if (e==null){
                    //done
                    Log.i("app","sign up succesfully")
                }else{Log.e("Signup","Failed "+e.message)}
            }
        })
    }
    fun checkLoggedIn() :Boolean{
        if (ParseUser.getCurrentUser()!=null){
            Log.i("Appp"," usr signed in succesfully")
            return true
        }else{
            Log.i("Appp","usr is not signin")
            return false
        }
        return false
    }
    fun tosinglerecord(parseUser:ParseUser,pass:String,):User{
        var user=User()
        user.ObjectId=parseUser.objectId
        user.userName=parseUser.username
        user.password=pass
        return user
    }

    fun LogInWithCallback(user: User, Callback: (returnedUser:User)->Unit) {

        ParseUser.logInInBackground(user.userName,user.password,
            {pUser,ex->


                if (pUser!=null && ex==null){
                    var type =pUser.get("status")
                    if(type==user.status){
                        Log.i("singin","sign in succesfully")
                        Callback(tosinglerecord(pUser,user.password.toString()))
                       AccountActivity.INSTANCE.progdialog!!.hide()
                    }

                }else{
                    AccountActivity.INSTANCE.progdialog!!.hide()
                   Toast.makeText(AccountActivity.INSTANCE.baseContext, "User Not Register", Toast.LENGTH_SHORT).show()
                //Callback(User())
                }
            })

    }

    fun signUpWithCallback(user: User, Callback: (returnedUser:User)->Unit) {

        parseuser?.setPassword(user.password)
        parseuser?.username=user.userName
        parseuser?.signUpInBackground(object :SignUpCallback{
            override fun done(e: ParseException?) {
                if (e==null){
                    //done
                    Log.i("app","sign up succesfully")
                    Callback(user)
                }else{
                    Log.e("Signup","Failed"+e.message.toString())
                    Callback(User())
                }
            }

        })
    }




    fun toLocalrecord(parseUser: ParseObject):User{
        var user=User()
        user.ObjectId=parseUser.objectId
        user.userName=parseUser.getString("username")
      //  user.location=parseUser.getParseGeoPoint("Location")

        return user
    }

   /* private fun showAlertDialog(s: String, s1: String) {
        val show = AlertDialog.Builder(AccountActivity.INSTANCE.applicationContext)
            .setTitle(s).setMessage(s1)
            .setMessage(s1)
            .setPositiveButton(android.R.string.ok) { dialog: DialogInterface?, which: Int -> }
            .setNegativeButton(android.R.string.cancel) { dialog: DialogInterface?, which: Int -> }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
    }*/




}