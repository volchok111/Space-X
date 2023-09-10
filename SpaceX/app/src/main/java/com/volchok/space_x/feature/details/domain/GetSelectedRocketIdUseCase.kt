package com.volchok.space_x.feature.details.domain

import com.volchok.space_x.library.use_case.domain.SynchronousUseCase

class GetSelectedRocketIdUseCase(
    private val repository: DetailsRepository
) : SynchronousUseCase<Unit, String> {
    override fun invoke(input: Unit): String = repository.selectedRocketId
}