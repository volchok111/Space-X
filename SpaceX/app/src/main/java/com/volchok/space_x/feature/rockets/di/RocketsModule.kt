package com.volchok.space_x.feature.rockets.di

import com.volchok.space_x.feature.rockets.domain.OpenRocketDetailsUseCase
import com.volchok.space_x.feature.rockets.presentation.RocketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val rocketsModule = module {
    viewModelOf(::RocketsViewModel)

    factoryOf(::OpenRocketDetailsUseCase)
}