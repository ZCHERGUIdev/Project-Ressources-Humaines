package com.example.projectleila.Ui.Account

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.projectleila.Dao.UserDao
import com.example.projectleila.Poko.User
import com.example.projectleila.R
import com.example.projectleila.Ui.Chef.ChefDeProjectActivity
import com.example.projectleila.Ui.Responsable.ResponsableQActivity
import com.example.projectleila.Ui.TChantier.TChantierActivity
import com.example.projectleila.Ui.UserType
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity() {
    var usedao: UserDao? = null
    var progdialog: ProgressDialog? = null

    enum class AccountStatus(var status: String) {
        LOGIN("login"), SIGNUP("signup")
    }

    companion object{
       val INSTANCE=AccountActivity()

    }
    var status: AccountStatus = AccountStatus.LOGIN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        supportActionBar!!.hide()
        //init dao

        var myintet=getIntent()
        val type=  myintet.getStringExtra("type")
       // Toast.makeText(this, "data : "+type, Toast.LENGTH_SHORT).show()

        usedao = UserDao()
        ParseUser.logOut()
        //init PrgDialog
        progdialog = ProgressDialog(this)
        progdialog?.setMessage("Pleaze Wait...")
        progdialog?.setCancelable(true)
       // btnSignupLogin.text = AccountStatus.LOGIN.toString()
       // textViewSignupLogin.text = AccountStatus.SIGNUP.toString()
        if (usedao?.checkLoggedIn()!!){
            Gotohomepage(type!!)
        }

        txtAdmin.setOnClickListener {
            var link ="https://amenhyd.admin.back4app.com/"
            val intent=Intent(Intent.ACTION_VIEW)
            intent.data=Uri.parse(link)
            startActivity(intent)
        }
    }


    fun doSignupOrLogin(view: View) {
        if (status == AccountStatus.LOGIN) {
// sign in the app
            var user = User(textUsername.text.toString(), textPassword.text.toString(), "", "")
            Login(user)

        } else if (status == AccountStatus.SIGNUP) {
            /*
            progdialog?.show()
            var user = User(textUsername.text.toString(), textPassword.text.toString(), "", "")
            usedao?.signUpWithCallback(user, { returnedUser ->
                progdialog?.hide()
                if (returnedUser.userName != null) {
                    Login(returnedUser)
                }
            })*/
        }

    }

    fun ActiveDefaultBehaviour(view: View) {
        var tv = view as TextView
        if (tv.text == AccountStatus.LOGIN.toString()) {
            status = AccountStatus.LOGIN
        } else if (tv.text == AccountStatus.SIGNUP.toString()) {
            status = AccountStatus.SIGNUP
        }
        if (status == AccountStatus.LOGIN) {
            //sign in
            btnSignupLogin.text = AccountStatus.LOGIN.toString()
            textViewSignupLogin.text = AccountStatus.SIGNUP.toString()

        } else if (status == AccountStatus.SIGNUP) {
            //sign up
            status = AccountStatus.SIGNUP
            btnSignupLogin.text = AccountStatus.SIGNUP.toString()
            textViewSignupLogin.text = AccountStatus.LOGIN.toString()
        }
    }

    private fun showAlertDialog(s: String, s1: String) {
        AlertDialog.Builder(this)
            .setTitle(s).setMessage(s1)
            .setMessage(s1)
            .setPositiveButton(android.R.string.ok) { dialog: DialogInterface?, which: Int -> }
            .setNegativeButton(android.R.string.cancel) { dialog: DialogInterface?, which: Int -> }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
    }
    fun Login(user:User){
        progdialog?.show()
        var myintet=getIntent()
        val type=  myintet.getStringExtra("type")
        var user = User(textUsername.text.toString(), textPassword.text.toString(), "", "",type!!)
        if (user.userName!!.isEmpty()|| user.password!!.isEmpty()){
            progdialog?.hide()
            Toast.makeText(this, "username or password is empty", Toast.LENGTH_SHORT).show()
        }
        usedao?.LogInWithCallback(user, { returnedUser ->
            progdialog?.hide()
            if (returnedUser.userName != null && returnedUser.password!=null) {
                //Go to home page
                var myintet=getIntent()
                val type=  myintet.getStringExtra("type")
                Gotohomepage(type!!)
            }else{
                progdialog?.hide()
                Toast.makeText(this, "something wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun Gotohomepage(data:String) {
        if (data=="chef"){
            startActivity(Intent(this.baseContext, ChefDeProjectActivity::class.java))
        }else if(data=="tchantier"){
            startActivity(Intent(this.baseContext, TChantierActivity::class.java))
        }else{
            startActivity(Intent(this.baseContext, ResponsableQActivity::class.java))
        }

    }

}