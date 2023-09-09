package com.volchok.space_x.app.domain

import com.volchok.space_x.app.model.NavigationEvent
import com.volchok.space_x.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveNavigationEventsUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Flow<NavigationEvent>> {

    override fun invoke(input: Unit): Flow<NavigationEvent> =
        mainNavigationController.navigationEvent
}