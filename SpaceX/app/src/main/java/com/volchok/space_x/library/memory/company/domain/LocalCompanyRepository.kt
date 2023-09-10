package com.volchok.space_x.library.memory.company.domain

import com.volchok.space_x.library.api.model.company.CompanyModel
import kotlinx.coroutines.flow.Flow

interface LocalCompanyRepository {
    val company: Flow<CompanyModel>

    fun set(company: CompanyModel)
}