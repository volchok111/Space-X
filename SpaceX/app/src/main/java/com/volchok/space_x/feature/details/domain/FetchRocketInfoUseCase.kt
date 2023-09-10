package com.volchok.space_x.feature.details.domain

import com.volchok.space_x.library.api.domain.RemoteRepository
import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.data.model.Data
import com.volchok.space_x.library.data.model.safeCall
import com.volchok.space_x.library.memory.details.domain.GetSelectedRocketIdUseCase
import com.volchok.space_x.library.memory.details.domain.LocalRocketRepository
import com.volchok.space_x.library.use_case.domain.SuspendUseCase
import com.volchok.space_x.library.use_case.domain.invoke

class FetchRocketInfoUseCase(
    private val repository: RemoteRepository,
    private val localRocketRepository: LocalRocketRepository,
    private val getSelectedRocketIdUseCase: GetSelectedRocketIdUseCase
) : SuspendUseCase<Unit, Data<RocketDetailsModel>> {
    override suspend fun invoke(input: Unit): Data<RocketDetailsModel> = safeCall {
        rocketInfo()
    }

    private suspend fun rocketInfo(): RocketDetailsModel {
        val result = repository.getRocketInfo(getSelectedRocketIdUseCase()).getSuccessValueOrThrow()
        val rocket = RocketDetailsModel(
            description = result.description,
            diameter = result.diameter,
            first_stage = result.first_stage,
            height = result.height,
            mass = result.mass,
            id = result.id,
            name = result.name,
            type = result.type,
            second_stage = result.second_stage,
            flickr_images = result.flickr_images,
        )
        localRocketRepository.set(rocket)
        return rocket
    }
}