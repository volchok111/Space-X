package com.volchok.space_x.library.api.domain

import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.api.model.rocket.RocketItem
import com.volchok.space_x.library.data.model.Data

interface RemoteRepository {

    suspend fun getRockets(): Data<List<RocketItem>>

    suspend fun getRocketInfo(id: String): Data<RocketDetailsModel>
}