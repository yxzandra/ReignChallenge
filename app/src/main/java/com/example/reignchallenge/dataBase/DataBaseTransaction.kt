package com.example.reignchallenge.dataBase

import io.realm.Realm
import io.realm.kotlin.createObject

class DataBaseTransaction{
    var myRealm: Realm = Realm.getDefaultInstance()

    fun addHit(id: Long, author:String, createAt:String, title: String, url: String ) {
        if(myRealm.where(HitTable::class.java).equalTo("id", id).findFirst() == null) {
            myRealm.beginTransaction()
            // Create an object
            val hitTable = myRealm.createObject<HitTable>(id)
            // Set its fields
            hitTable.author = author
            hitTable.createdAt = createAt
            hitTable.url = url
            hitTable.title = title
            myRealm.commitTransaction()
        }
    }

    fun getAll(): List<HitTable> {
        return myRealm.where(HitTable::class.java).findAll().toList()
    }

    fun deleteHit(id: String) {
        myRealm.beginTransaction()
        myRealm.where(HitTable::class.java).equalTo("id", id).findFirst()!!.deleteFromRealm()
        myRealm.commitTransaction()

    }


}