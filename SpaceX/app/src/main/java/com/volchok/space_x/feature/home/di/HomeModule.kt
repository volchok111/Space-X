package com.volchok.space_x.feature.home.di

import com.volchok.space_x.feature.home.domain.OpenRocketDetailsUseCase
import com.volchok.space_x.feature.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)

    factoryOf(::OpenRocketDetailsUseCase)
}