package com.it.yousefl.mstarttask

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication :Application(){
     lateinit var applicationInstance: BaseApplication

    override fun onCreate() {
        super.onCreate()
        applicationInstance = this
    }

    operator fun get(context: Context): BaseApplication? {
        return context.applicationContext as BaseApplication
    }

    @Synchronized
    fun getInstance(): BaseApplication? {
        return applicationInstance
    }

}
