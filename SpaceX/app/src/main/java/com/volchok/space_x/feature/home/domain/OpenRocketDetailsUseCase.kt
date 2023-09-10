package com.volchok.space_x.feature.home.domain

import com.volchok.space_x.library.rockets.domain.DetailsRepository
import com.volchok.space_x.library.use_case.domain.SynchronousUseCase

class OpenRocketDetailsUseCase(
    private val homeNavigationController: HomeNavigationController,
    private val detailsRepository: DetailsRepository
) : SynchronousUseCase<String, Unit> {
    override fun invoke(input: String) {
        detailsRepository.selectedRocketId = input
        homeNavigationController.goToRocketDetails()
    }
}