package com.example.projectleila.Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectleila.R
import com.example.projectleila.Ui.Chef.ChefDeProjectActivity
import com.example.projectleila.Ui.Responsable.ResponsableQActivity
import com.example.projectleila.Ui.TChantier.TChantierActivity
import kotlinx.android.synthetic.main.activity_main.*

class UserType : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.hide()
        cproject.setOnClickListener {
           startActivity(Intent(this, ChefDeProjectActivity::class.java))
        }

        tqualité.setOnClickListener {
            startActivity(Intent(this, TChantierActivity::class.java))        }
        respoQali.setOnClickListener {
            startActivity(Intent(this, ResponsableQActivity::class.java))
        }
    }

}