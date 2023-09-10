package com.volchok.space_x.app.di

import com.volchok.space_x.app.device.GlobalNavigationController
import com.volchok.space_x.app.domain.MainNavigationController
import com.volchok.space_x.app.domain.ObserveNavigationEventsUseCase
import com.volchok.space_x.app.presentation.MainViewModel
import com.volchok.space_x.feature.home.domain.HomeNavigationController
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.binds
import org.koin.dsl.module

internal val mainModule = module {
    viewModelOf(::MainViewModel)
    factory { ObserveNavigationEventsUseCase(get()) }

    single { GlobalNavigationController() }.binds(
        arrayOf(
            HomeNavigationController::class,
            MainNavigationController::class
        )
    )
}