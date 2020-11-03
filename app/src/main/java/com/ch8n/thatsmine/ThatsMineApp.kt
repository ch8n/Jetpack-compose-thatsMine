package com.ch8n.thatsmine

import android.app.Application
import com.ch8n.thatsmine.base.utils.AdvanceDebugTree
import timber.log.Timber

class ThatsMineApp : Application() {

    override fun onCreate() {
        super.onCreate()
        // todo add gradle field to check tree to plant
        Timber.plant(AdvanceDebugTree())
    }
}