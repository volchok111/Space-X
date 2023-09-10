package com.volchok.space_x.library.memory.di

import com.volchok.space_x.library.memory.data.MemoryDetailsRepository
import com.volchok.space_x.library.memory.data.MemoryRocketDetailsRepository
import com.volchok.space_x.library.memory.domain.DetailsRepository
import com.volchok.space_x.library.memory.domain.FetchRocketInfoUseCase
import com.volchok.space_x.library.memory.domain.GetSelectedRocketIdUseCase
import com.volchok.space_x.library.memory.domain.LocalRocketRepository
import com.volchok.space_x.library.memory.domain.ObserveRocketDetailsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val memoryModule = module {
    factoryOf(::FetchRocketInfoUseCase)
    factoryOf(::GetSelectedRocketIdUseCase)
    factoryOf(::ObserveRocketDetailsUseCase)

    singleOf(::MemoryRocketDetailsRepository) bind LocalRocketRepository::class
    singleOf(::MemoryDetailsRepository) bind DetailsRepository::class
}