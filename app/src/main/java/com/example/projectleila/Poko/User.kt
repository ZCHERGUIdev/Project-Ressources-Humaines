package com.example.projectleila.Poko

class User {
    var userName:String? =null
    var userEmail:String? =null
    var ObjectId:String? =null
    var password:String? =null
    var status:String? =null
       constructor(){}
    constructor(u:String,p:String,e:String,id:String,s:String){
        this.userName=u
        this.password=p
        this.userEmail=e
        this.ObjectId=id
        this.status=s
    }
    constructor(u:String,p:String,e:String,id:String){
        this.userName=u
        this.password=p
        this.userEmail=e
        this.ObjectId=id

    }
}