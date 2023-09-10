package com.volchok.space_x.library.memory.di

import com.volchok.space_x.library.memory.company.data.MemoryCompanyRepository
import com.volchok.space_x.library.memory.company.domain.LocalCompanyRepository
import com.volchok.space_x.library.memory.details.data.MemoryDetailsRepository
import com.volchok.space_x.library.memory.details.data.MemoryRocketDetailsRepository
import com.volchok.space_x.library.memory.details.domain.DetailsRepository
import com.volchok.space_x.library.memory.details.domain.GetSelectedRocketIdUseCase
import com.volchok.space_x.library.memory.details.domain.LocalRocketRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val memoryModule = module {
    factoryOf(::GetSelectedRocketIdUseCase)

    singleOf(::MemoryRocketDetailsRepository) bind LocalRocketRepository::class
    singleOf(::MemoryDetailsRepository) bind DetailsRepository::class
    singleOf(::MemoryCompanyRepository) bind LocalCompanyRepository::class
}