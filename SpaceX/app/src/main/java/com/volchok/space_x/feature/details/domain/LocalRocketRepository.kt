package com.volchok.space_x.feature.details.domain

import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import kotlinx.coroutines.flow.Flow

interface LocalRocketRepository {
    val rocket: Flow<RocketDetailsModel>

    fun set(rocket: RocketDetailsModel)
}