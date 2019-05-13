package com.example.reignchallenge.dataBase

import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.createObject

class DataBaseTransaction{
    var myRealm: Realm = Realm.getDefaultInstance()

    fun addHit(id: Long, author:String, createAt:String, title: String, url: String ) {
        val isNotContain = myRealm.where(HitTable::class.java).equalTo("id", id).findFirst() == null
        val isNotDeleted = myRealm.where(HitDeletedTable::class.java).equalTo("id", id).findFirst() == null

        if(isNotContain && isNotDeleted) {
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
        return myRealm.where(HitTable::class.java).findAll().sort("createdAt", Sort.DESCENDING)
    }

    fun deleteHit(id: Long) {
        myRealm.beginTransaction()
        addedHitDeletedTable(id)
        myRealm.where(HitTable::class.java).equalTo("id", id).findFirst()!!.deleteFromRealm()
        myRealm.commitTransaction()

    }

    private fun addedHitDeletedTable(id: Long) {
        if(myRealm.where(HitDeletedTable::class.java).equalTo("id", id).findFirst() == null) {
            // Create an object
            myRealm.createObject<HitDeletedTable>(id)
        }

    }


}