package com.selen.myweather.di

import android.content.Context
import com.selen.myweather.app.App
import dagger.Module
import dagger.Provides

/**
 * @author Pyaterko Aleksey
 */
@Module(
    includes = [
        CityPrefModule::class,
        RoomModule::class
    ]
)
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun getAppContext(): Context {
        return app
    }
}