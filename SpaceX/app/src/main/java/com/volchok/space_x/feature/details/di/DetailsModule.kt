package com.volchok.space_x.feature.details.di

import com.volchok.space_x.feature.details.domain.FetchRocketInfoUseCase
import com.volchok.space_x.feature.details.presentation.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val detailsModule = module {
    viewModelOf(::DetailsViewModel)
    factoryOf(::FetchRocketInfoUseCase)
}