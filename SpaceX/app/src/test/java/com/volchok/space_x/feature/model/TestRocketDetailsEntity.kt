package com.volchok.space_x.feature.model

import com.volchok.space_x.library.api.model.details.Diameter
import com.volchok.space_x.library.api.model.details.FirstStage
import com.volchok.space_x.library.api.model.details.Height
import com.volchok.space_x.library.api.model.details.Mass
import com.volchok.space_x.library.api.model.details.RocketDetailsModel
import com.volchok.space_x.library.api.model.details.SecondStage

val testRocketDetailsData = RocketDetailsModel(
    description = "aaa",
    diameter = Diameter(2.0, 2.0),
    first_stage = FirstStage(
        burn_time_sec = 3,
        engines = 2,
        fuel_amount_tons = 4.0,
        reusable = true
    ),
    height = Height(2.0, 2.0),
    id = "aaa",
    mass = Mass(2, 3),
    name = "Falcon",
    type = "rocket",
    second_stage = SecondStage(
        burn_time_sec = 4,
        engines = 1,
        fuel_amount_tons = 6.0,
        reusable = false
    ),
    flickr_images = emptyList()
)