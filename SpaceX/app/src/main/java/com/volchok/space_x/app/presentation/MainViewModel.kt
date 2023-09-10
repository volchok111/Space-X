package com.volchok.space_x.app.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.space_x.app.domain.GoToHomeUseCase
import com.volchok.space_x.app.domain.GoToRocketsUseCase
import com.volchok.space_x.app.domain.ObserveNavigationEventsUseCase
import com.volchok.space_x.app.model.ForwardNavigationEvent
import com.volchok.space_x.app.model.NavigationEvent
import com.volchok.space_x.app.model.Route
import com.volchok.space_x.library.mvvm.presentation.AbstractViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val goToHomeUseCase: GoToHomeUseCase,
    private val goToRocketsUseCase: GoToRocketsUseCase,
    private val observeNavigationEventsUseCase: ObserveNavigationEventsUseCase
) : AbstractViewModel<MainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeNavigationEventsUseCase(Unit).collect { onNavigationEvent(it) }
        }
    }

    fun onHome() {
        goToHomeUseCase(Unit)
    }

    fun onRockets() {
        goToRocketsUseCase(Unit)
    }

    fun onNavigationEventConsumed() {
        state = state.copy(navigationEvent = null)
    }

    private fun onNavigationEvent(navigationEvent: NavigationEvent) {
        state = state.copy(navigationEvent = navigationEvent)
    }

    data class State(
        val navigationEvent: NavigationEvent? = ForwardNavigationEvent(Route.Initial)
    ) : AbstractViewModel.State
}