package com.selen.myweather.app

import android.app.Application
import com.selen.myweather.di.AppComponent
import com.selen.myweather.di.AppModule
import com.selen.myweather.di.DaggerAppComponent


/**
 * @author Pyaterko Aleksey
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        val LOG_TAG = "LOG_TAG"
        lateinit var instance: App
    }
}