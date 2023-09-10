package com.volchok.space_x.feature.home.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.space_x.feature.home.domain.OpenRocketDetailsUseCase
import com.volchok.space_x.library.api.domain.ObserveRocketsUseCase
import com.volchok.space_x.library.api.model.rocket.RocketItem
import com.volchok.space_x.library.data.model.Data
import com.volchok.space_x.library.mvvm.presentation.AbstractViewModel
import com.volchok.space_x.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class HomeViewModel(
    private val observeRocketsUseCase: ObserveRocketsUseCase,
    private val openRocketDetailsUseCase: OpenRocketDetailsUseCase
) : AbstractViewModel<HomeViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeRocketsUseCase().collect { list ->
                if (list is Data.Success) {
                    state = state.copy(rockets = list.value.map { it.toItem() }, loading = false)
                }
            }
        }
    }

    fun onItem(id: String) {
        openRocketDetailsUseCase(id)
    }

    private fun RocketItem.toItem() = State.RocketItem(
        first_flight,
        name,
        id
    )

    data class State(
        val rockets: List<RocketItem> = emptyList(),
        val loading: Boolean = true
    ) : AbstractViewModel.State {
        data class RocketItem(
            val first_flight: String? = null,
            val name: String? = null,
            val id: String? = null
        )
    }
}