package com.example.reignchallenge

import android.app.Application
import io.realm.Realm

class ReignApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this)
    }
}
