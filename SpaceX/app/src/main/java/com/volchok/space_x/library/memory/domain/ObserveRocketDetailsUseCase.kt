package com.volchok.space_x.library.memory.domain

import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveRocketDetailsUseCase(
    private val localRocketRepository: LocalRocketRepository
) : SynchronousUseCase<Unit, Flow<RocketDetailsModel>> {
    override fun invoke(input: Unit): Flow<RocketDetailsModel> = localRocketRepository.rocket
}