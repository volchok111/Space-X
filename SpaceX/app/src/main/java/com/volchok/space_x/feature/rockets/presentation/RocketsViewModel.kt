package com.volchok.space_x.feature.rockets.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.space_x.feature.rockets.domain.OpenRocketDetailsUseCase
import com.volchok.space_x.library.api.domain.ObserveRocketsUseCase
import com.volchok.space_x.library.api.model.rocket.RocketItem
import com.volchok.space_x.library.data.model.Data
import com.volchok.space_x.library.mvvm.presentation.AbstractViewModel
import com.volchok.space_x.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class RocketsViewModel(
    private val observeRocketsUseCase: ObserveRocketsUseCase,
    private val openRocketDetailsUseCase: OpenRocketDetailsUseCase
) : AbstractViewModel<RocketsViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            observeRocketsUseCase().collect { list ->
                if (list is Data.Success) {
                    state = state.copy(
                        rockets = list.value.map { it.toItem() },
                        filteredRockets = list.value.sortedByDescending { item -> item.first_flight }
                            .map { it.toItem() },
                        loading = false
                    )
                }
            }
        }
    }

    fun onFilterButtonClicked() {
        state = if (!state.isFilterButtonCLicked) {
            state.copy(isFilterButtonCLicked = true)
        } else {
            state.copy(isFilterButtonCLicked = false)
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
        val filteredRockets: List<RocketItem> = emptyList(),
        val loading: Boolean = true,
        val isFilterButtonCLicked: Boolean = false
    ) : AbstractViewModel.State {
        data class RocketItem(
            val first_flight: String? = null,
            val name: String? = null,
            val id: String? = null
        )
    }
}