package com.example.reignchallenge.dataBase

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class HitDeletedTable(
    @PrimaryKey var id: Long = 0
) : RealmObject()