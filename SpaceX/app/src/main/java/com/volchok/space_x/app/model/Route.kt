package com.volchok.space_x.app.model

enum class Route {
    Company,
    Details,
    Rockets;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Company
    }
}