package com.volchok.space_x.feature.details.di

import com.volchok.space_x.feature.details.data.MemoryDetailsRepository
import com.volchok.space_x.feature.details.data.MemoryRocketDetailsRepository
import com.volchok.space_x.feature.details.domain.DetailsRepository
import com.volchok.space_x.feature.details.domain.FetchRocketInfoUseCase
import com.volchok.space_x.feature.details.domain.GetSelectedRocketIdUseCase
import com.volchok.space_x.feature.details.domain.LocalRocketRepository
import com.volchok.space_x.feature.details.domain.ObserveRocketDetailsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val detailsModule = module {
    factoryOf(::FetchRocketInfoUseCase)
    factoryOf(::GetSelectedRocketIdUseCase)
    factoryOf(::ObserveRocketDetailsUseCase)

    singleOf(::MemoryRocketDetailsRepository) bind LocalRocketRepository::class
    singleOf(::MemoryDetailsRepository) bind DetailsRepository::class
}