package com.volchok.space_x.app.model

enum class Route {
    Home;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Home
    }
}