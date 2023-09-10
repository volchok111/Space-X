package com.volchok.space_x.feature.company.di

import com.volchok.space_x.feature.company.presentation.CompanyViewModel
import com.volchok.space_x.library.memory.company.domain.FetchCompanyUseCase
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val companyModule = module {
    viewModelOf(::CompanyViewModel)
    factoryOf(::FetchCompanyUseCase)
}