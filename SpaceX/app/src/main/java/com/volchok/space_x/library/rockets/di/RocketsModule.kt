package com.volchok.space_x.library.rockets.di

import com.volchok.space_x.library.rockets.data.MemoryDetailsRepository
import com.volchok.space_x.library.rockets.data.MemoryRocketDetailsRepository
import com.volchok.space_x.library.rockets.domain.DetailsRepository
import com.volchok.space_x.library.rockets.domain.FetchRocketInfoUseCase
import com.volchok.space_x.library.rockets.domain.GetSelectedRocketIdUseCase
import com.volchok.space_x.library.rockets.domain.LocalRocketRepository
import com.volchok.space_x.library.rockets.domain.ObserveRocketDetailsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val rocketsModule = module {
    factoryOf(::FetchRocketInfoUseCase)
    factoryOf(::GetSelectedRocketIdUseCase)
    factoryOf(::ObserveRocketDetailsUseCase)

    singleOf(::MemoryRocketDetailsRepository) bind LocalRocketRepository::class
    singleOf(::MemoryDetailsRepository) bind DetailsRepository::class
}