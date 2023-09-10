package com.volchok.space_x.library.api.domain

import com.volchok.space_x.library.api.model.company.CompanyModel
import com.volchok.space_x.library.memory.company.domain.LocalCompanyRepository
import com.volchok.space_x.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveCompanyUseCase(
    private val localCompanyRepository: LocalCompanyRepository
) : SynchronousUseCase<Unit, Flow<CompanyModel>> {
    override fun invoke(input: Unit): Flow<CompanyModel> = localCompanyRepository.company

}