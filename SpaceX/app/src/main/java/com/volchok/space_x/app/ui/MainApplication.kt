package com.volchok.space_x.app.ui

import android.app.Application
import com.volchok.space_x.app.di.mainModule
import com.volchok.space_x.feature.details.di.detailsModule
import com.volchok.space_x.feature.home.di.homeModule
import com.volchok.space_x.library.api.di.apiModule
import com.volchok.space_x.library.rockets.di.rocketsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(applicationContext)
            modules(
                apiModule,
                detailsModule,
                homeModule,
                mainModule,
                rocketsModule
            )
        }
        super.onCreate()
    }
}