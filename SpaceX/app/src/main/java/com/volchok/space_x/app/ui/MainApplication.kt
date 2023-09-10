package com.volchok.space_x.app.ui

import android.app.Application
import com.volchok.space_x.app.di.mainModule
import com.volchok.space_x.feature.company.di.companyModule
import com.volchok.space_x.feature.details.di.detailsModule
import com.volchok.space_x.feature.rockets.di.rocketsModule
import com.volchok.space_x.library.api.di.apiModule
import com.volchok.space_x.library.memory.di.memoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        startKoin {
            androidContext(applicationContext)
            modules(
                apiModule,
                companyModule,
                detailsModule,
                mainModule,
                memoryModule,
                rocketsModule
            )
        }
        super.onCreate()
    }
}