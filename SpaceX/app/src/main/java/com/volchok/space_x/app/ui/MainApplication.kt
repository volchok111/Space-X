package com.volchok.space_x.app.ui

import android.app.Application
import com.volchok.space_x.app.di.mainModule
import com.volchok.space_x.library.api.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(applicationContext)
            modules(
                mainModule,
                apiModule
            )
        }
        super.onCreate()
    }
}