package com.volchok.space_x.library.memory.company.data

import com.volchok.space_x.library.api.model.company.CompanyModel
import com.volchok.space_x.library.memory.company.domain.LocalCompanyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MemoryCompanyRepository : LocalCompanyRepository {
    private val _company = MutableStateFlow<CompanyModel?>(null)
    override val company: Flow<CompanyModel> = _company.filterNotNull()

    override fun set(company: CompanyModel) {
        _company.tryEmit(company)
    }
}