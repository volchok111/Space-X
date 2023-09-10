package com.volchok.space_x.library.api.model.details

data class RocketDetailsModel(
    val description: String,
    val diameter: Diameter,
    val first_stage: FirstStage,
    val flickr_images: List<String>,
    val height: Height,
    val id: String,
    val mass: Mass,
    val name: String,
    val second_stage: SecondStage,
    val type: String,
)