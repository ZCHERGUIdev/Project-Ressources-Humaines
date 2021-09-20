package com.example.projectleila

import android.app.Application
import android.provider.UserDictionary.Words.APP_ID
import com.parse.Parse
import com.parse.ParseACL

class App:Application() {

    override fun onCreate() {
        super.onCreate()


        Parse.enableLocalDatastore(this)
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("1meepQhGegk0jVeBcFvOCrW6CUDOE31egQhXEXTJ") // if defined
                .clientKey("eUUOHkRNe6sQCBKu5anLtnv59wwbYlzb2EuS9sej")
                .server("https://parseapi.back4app.com")
                .build()
        )
        var parseAcl= ParseACL()
        parseAcl.publicWriteAccess=true
        parseAcl.publicReadAccess=true
        ParseACL.setDefaultACL(parseAcl,true)




    }

}