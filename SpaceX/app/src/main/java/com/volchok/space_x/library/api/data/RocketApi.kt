package com.volchok.space_x.library.api.data

import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.api.model.rocket.RocketItem
import retrofit2.http.GET
import retrofit2.http.Path

interface RocketApi {

    @GET("rockets")
    suspend fun getRockets(): List<RocketItem>

    @GET("rockets/{id}")
    suspend fun getRocketInfo(
        @Path(value = "id") id: String
    ): RocketDetailsModel
}