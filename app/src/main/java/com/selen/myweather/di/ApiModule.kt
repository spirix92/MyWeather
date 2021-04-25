package com.selen.myweather.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.selen.myweather.api.DefaultApiWorker
import com.selen.myweather.api.WeatherApi
import com.selen.myweather.api.WeatherApiWorker
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Pyaterko Aleksey
 */
@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String {
        return "https://api.openweathermap.org"
    }

    @Singleton
    @Provides
    fun initGSON(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun initRetrofit(@Named("baseUrl") baseUrl: String, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun initApiWorker(retrofit: Retrofit): WeatherApiWorker {
        val api = retrofit.create(WeatherApi::class.java)
        return DefaultApiWorker(api)
    }

}