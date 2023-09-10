package com.volchok.space_x.feature.rockets.domain

import com.volchok.space_x.library.memory.domain.DetailsRepository
import com.volchok.space_x.library.use_case.domain.SynchronousUseCase

class OpenRocketDetailsUseCase(
    private val rocketsNavigationController: RocketsNavigationController,
    private val detailsRepository: DetailsRepository
) : SynchronousUseCase<String, Unit> {
    override fun invoke(input: String) {
        detailsRepository.selectedRocketId = input
        rocketsNavigationController.goToRocketDetails()
    }
}