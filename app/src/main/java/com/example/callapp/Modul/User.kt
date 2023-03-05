package com.example.callapp.Modul

class Users(val Address:String? = null ,
                val Number:String? = null ,
                val Name:String? = null){
    companion object{
        val FIRESTORE_COLLECTION_NAME = "Users"
    }
}



