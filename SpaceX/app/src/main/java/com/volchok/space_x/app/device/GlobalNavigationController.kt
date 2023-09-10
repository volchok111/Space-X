package com.volchok.space_x.app.device

import com.volchok.space_x.app.domain.MainNavigationController
import com.volchok.space_x.app.model.BackNavigationEvent
import com.volchok.space_x.app.model.ForwardNavigationEvent
import com.volchok.space_x.app.model.NavigationEvent
import com.volchok.space_x.app.model.Route
import com.volchok.space_x.feature.home.domain.HomeNavigationController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class GlobalNavigationController :
    MainNavigationController,
    HomeNavigationController {

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override fun goBack() = goTo(BackNavigationEvent)

    override fun goToHome() = goTo(ForwardNavigationEvent(Route.Home, true))

    override fun goToRockets() = goTo(ForwardNavigationEvent(Route.Rockets))

    override fun goToRocketDetails() = goTo(ForwardNavigationEvent(Route.Details))

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }
}