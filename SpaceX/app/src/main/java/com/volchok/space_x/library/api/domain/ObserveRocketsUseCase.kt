package com.volchok.space_x.library.api.domain

import com.volchok.space_x.library.api.model.rocket.RocketItem
import com.volchok.space_x.library.data.model.Data
import com.volchok.space_x.library.use_case.domain.SuspendUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ObserveRocketsUseCase(
    private val repository: RemoteRepository
) : SuspendUseCase<Unit, Flow<Data<List<RocketItem>>>> {
    override suspend fun invoke(input: Unit): Flow<Data<List<RocketItem>>> = flow {
        emit(repository.getRockets())
    }
}