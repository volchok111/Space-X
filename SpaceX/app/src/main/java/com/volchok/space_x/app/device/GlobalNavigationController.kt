package com.volchok.space_x.app.device

import com.volchok.space_x.app.domain.MainNavigationController
import com.volchok.space_x.app.model.BackNavigationEvent
import com.volchok.space_x.app.model.NavigationEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class GlobalNavigationController :
    MainNavigationController {

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override fun goBack() = goTo(BackNavigationEvent)

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }
}