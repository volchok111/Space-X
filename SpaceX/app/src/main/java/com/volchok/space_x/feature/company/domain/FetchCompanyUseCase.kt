package com.volchok.space_x.feature.company.domain

import com.volchok.space_x.library.api.domain.RemoteRepository
import com.volchok.space_x.library.api.model.company.CompanyModel
import com.volchok.space_x.library.data.model.Data
import com.volchok.space_x.library.data.model.safeCall
import com.volchok.space_x.library.memory.company.domain.LocalCompanyRepository
import com.volchok.space_x.library.use_case.domain.SuspendUseCase

class FetchCompanyUseCase(
    private val repository: RemoteRepository,
    private val localCompanyRepository: LocalCompanyRepository
) : SuspendUseCase<Unit, Data<CompanyModel>> {
    override suspend fun invoke(input: Unit): Data<CompanyModel> = safeCall {
        companyInfo()
    }

    private suspend fun companyInfo(): CompanyModel {
        val result = repository.getCompany().getSuccessValueOrThrow()
        val company = CompanyModel(
            ceo = result.ceo,
            coo = result.coo,
            employees = result.employees,
            founded = result.founded,
            headquarters = result.headquarters,
            id = result.id,
            links = result.links,
            name = result.name,
            summary = result.summary,
            valuation = result.valuation
        )
        localCompanyRepository.set(company)
        return company
    }
}