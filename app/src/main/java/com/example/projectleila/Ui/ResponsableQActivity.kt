package com.example.projectleila.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectleila.R

class ResponsableQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_responsable_qactivity)
        supportActionBar!!.hide()
    }
}