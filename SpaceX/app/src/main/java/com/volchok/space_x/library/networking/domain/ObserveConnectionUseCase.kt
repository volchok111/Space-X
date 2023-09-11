package com.volchok.space_x.library.networking.domain

import com.volchok.space_x.library.networking.model.NetworkConnection
import com.volchok.space_x.library.use_case.domain.SynchronousUseCase
import kotlinx.coroutines.flow.Flow

class ObserveConnectionUseCase internal constructor(
    private val networkController: NetworkController,
) : SynchronousUseCase<Unit, Flow<NetworkConnection>> {

    override fun invoke(input: Unit): Flow<NetworkConnection> =
        networkController.observeNetworkChange()
}