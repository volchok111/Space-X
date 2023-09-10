package com.volchok.space_x.feature.details.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.space_x.library.rockets.domain.FetchRocketInfoUseCase
import com.volchok.space_x.library.rockets.domain.ObserveRocketDetailsUseCase
import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.mvvm.presentation.AbstractViewModel
import com.volchok.space_x.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val fetchRocketInfoUseCase: FetchRocketInfoUseCase,
    private val observeRocketDetailsUseCase: ObserveRocketDetailsUseCase
) : AbstractViewModel<DetailsViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            fetchRocketInfoUseCase()
        }

        viewModelScope.launch {
            observeRocketDetailsUseCase().collect {
                state = state.copy(rocket = it, loading = false)
            }
        }
    }

    data class State(
        val loading: Boolean = true,
        val rocket: RocketDetailsModel? = null
    ) : AbstractViewModel.State
}