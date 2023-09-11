package com.volchok.space_x.app.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.space_x.app.domain.GoToCompanyUseCase
import com.volchok.space_x.app.domain.GoToRocketsUseCase
import com.volchok.space_x.app.domain.ObserveNavigationEventsUseCase
import com.volchok.space_x.app.model.ForwardNavigationEvent
import com.volchok.space_x.app.model.NavigationEvent
import com.volchok.space_x.app.model.Route
import com.volchok.space_x.library.mvvm.presentation.AbstractViewModel
import com.volchok.space_x.library.networking.domain.ObserveConnectionUseCase
import com.volchok.space_x.library.networking.model.NetworkConnection
import kotlinx.coroutines.launch

class MainViewModel(
    private val goToCompanyUseCase: GoToCompanyUseCase,
    private val goToRocketsUseCase: GoToRocketsUseCase,
    private val observeNavigationEventsUseCase: ObserveNavigationEventsUseCase,
    private val observeConnectionUseCase: ObserveConnectionUseCase
) : AbstractViewModel<MainViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeNavigationEventsUseCase(Unit).collect { onNavigationEvent(it) }
        }
        viewModelScope.launch {
            observeConnectionUseCase(Unit).collect { onConnectionChanged(it) }
        }
    }

    fun onCompany() {
        goToCompanyUseCase(Unit)
    }

    fun onRockets() {
        goToRocketsUseCase(Unit)
    }

    fun onNavigationEventConsumed() {
        state = state.copy(navigationEvent = null)
    }

    private fun onConnectionChanged(connection: NetworkConnection) {
        state = state.copy(isOffline = connection == NetworkConnection.Offline)
    }

    private fun onNavigationEvent(navigationEvent: NavigationEvent) {
        state = state.copy(navigationEvent = navigationEvent)
    }

    data class State(
        val isOffline: Boolean = false,
        val navigationEvent: NavigationEvent? = ForwardNavigationEvent(Route.Initial)
    ) : AbstractViewModel.State
}