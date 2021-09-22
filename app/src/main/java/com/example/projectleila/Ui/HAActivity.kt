package com.example.projectleila.Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectleila.R
import com.parse.*
import kotlinx.android.synthetic.main.activity_haactivity.*
import kotlinx.android.synthetic.main.activity_htactivity.*
import kotlinx.android.synthetic.main.activity_htactivity.btnSave
import kotlinx.android.synthetic.main.activity_htactivity.txtHT

class HAActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haactivity)
        btnSave.setOnClickListener {
            val query = ParseQuery.getQuery<ParseObject>("Project")
            query.getFirstInBackground(object : GetCallback<ParseObject> {
                override fun done(`object`: ParseObject?, e: ParseException?) {
                    if (e == null) {

                        val objectID = `object`!!.objectId.toString();
                        query.getInBackground(objectID, object : GetCallback<ParseObject> {
                            override fun done(`object`: ParseObject?, e: ParseException?) {
                                if (e == null) {

                                    `object`!!.put("HA", txtHarret.text.toString())
                                    `object`!!.put("C", txtCause.text.toString())
                                    `object`.saveInBackground(object : SaveCallback {
                                        override fun done(e: ParseException?) {
                                        }

                                    })
                                }
                            }

                        })

                    }
                }

            })
        }
    }
}