package com.volchok.space_x.feature.company.presentation

import androidx.lifecycle.viewModelScope
import com.volchok.space_x.library.api.domain.ObserveCompanyUseCase
import com.volchok.space_x.library.api.model.company.CompanyModel
import com.volchok.space_x.feature.company.domain.FetchCompanyUseCase
import com.volchok.space_x.library.mvvm.presentation.AbstractViewModel
import com.volchok.space_x.library.use_case.domain.invoke
import kotlinx.coroutines.launch

class CompanyViewModel(
    private val fetchCompanyUseCase: FetchCompanyUseCase,
    private val observeCompanyUseCase: ObserveCompanyUseCase
): AbstractViewModel<CompanyViewModel.State>(State()) {

    init {
        viewModelScope.launch {
            fetchCompanyUseCase()
        }

        viewModelScope.launch {
            observeCompanyUseCase().collect {
                state = state.copy(company = it, loading = false)
            }
        }
    }

    data class State(
        val company: CompanyModel? = null,
        val loading: Boolean = true
    ): AbstractViewModel.State
}