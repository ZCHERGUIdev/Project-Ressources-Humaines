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
                .applicationId("egTCEGDDRb9cruKVcnICmJWYgIIVLLNY5Ff7UE19") // if defined
                .clientKey("djupD3XKA3Ckp8NNyLW6TdtpUoP3lFEfapAEhzcj")
                .server("https://parseapi.back4app.com")
                .build()
        )
        var parseAcl= ParseACL()
        parseAcl.publicWriteAccess=true
        parseAcl.publicReadAccess=true
        ParseACL.setDefaultACL(parseAcl,true)




    }

}