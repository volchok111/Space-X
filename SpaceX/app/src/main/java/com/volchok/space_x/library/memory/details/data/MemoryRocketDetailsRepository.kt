package com.volchok.space_x.library.memory.details.data

import com.volchok.space_x.library.memory.details.domain.LocalRocketRepository
import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MemoryRocketDetailsRepository : LocalRocketRepository {
    private val _rocket = MutableStateFlow<RocketDetailsModel?>(null)
    override val rocket: Flow<RocketDetailsModel> = _rocket.filterNotNull()

    override fun set(rocket: RocketDetailsModel) {
        _rocket.tryEmit(rocket)
    }
}