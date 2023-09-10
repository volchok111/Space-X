package com.volchok.space_x.feature.company.di

import com.volchok.space_x.library.memory.company.domain.FetchCompanyUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val companyModule = module {
    factoryOf(::FetchCompanyUseCase)
}