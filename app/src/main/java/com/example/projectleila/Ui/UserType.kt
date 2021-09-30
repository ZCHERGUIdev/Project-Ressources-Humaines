package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectleila.R
import com.example.projectleila.Ui.Account.AccountActivity
import com.example.projectleila.Ui.Chef.ChefDeProjectActivity
import com.example.projectleila.Ui.Responsable.ResponsableQActivity
import com.example.projectleila.Ui.TChantier.TChantierActivity
import kotlinx.android.synthetic.main.activity_main.*

class UserType : AppCompatActivity() {
    var ccproject:String="chef"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    var ttqualité="tchantier"
    var rrespoQali="rcontrol"
        supportActionBar!!.hide()
        cproject.setOnClickListener {
          var myintet=Intent(this@UserType, AccountActivity::class.java)
            myintet.putExtra("type",ccproject)

          startActivity(myintet)
        }

        tqualité.setOnClickListener {
            var myintet=Intent(this@UserType, AccountActivity::class.java)
            myintet.putExtra("type",ttqualité)

            startActivity(myintet)
        }


        respoQali.setOnClickListener {
            var myintet=Intent(this@UserType, AccountActivity::class.java)
            myintet.putExtra("type",rrespoQali)

            startActivity(myintet)
        }
    }

}