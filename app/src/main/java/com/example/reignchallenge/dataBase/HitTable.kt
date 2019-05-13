package com.example.reignchallenge.dataBase

import io.realm.RealmObject import io.realm.annotations.PrimaryKey

open class HitTable(
    @PrimaryKey var id: Long = 0,

    var author: String = "",

    var createdAt: String = "",

    var title: String = "",

    var url: String = ""

) : RealmObject()