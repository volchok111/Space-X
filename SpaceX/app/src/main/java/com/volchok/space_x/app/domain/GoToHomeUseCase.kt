package com.volchok.space_x.app.domain

import com.volchok.space_x.library.use_case.domain.SynchronousUseCase

class GoToHomeUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Unit> {
    override fun invoke(input: Unit) {
        mainNavigationController.goToHome()
    }
}