package com.volchok.space_x.app.model

enum class Route {
    Rockets,
    Details,
    Home;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Home
    }
}