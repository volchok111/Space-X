package com.volchok.space_x.library.networking.domain

import com.volchok.space_x.library.networking.model.NetworkConnection
import kotlinx.coroutines.flow.Flow

internal interface NetworkController {
    fun observeNetworkChange(): Flow<NetworkConnection>
}