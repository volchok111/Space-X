package com.volchok.space_x.app.domain

import com.volchok.space_x.app.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

interface MainNavigationController {
    val navigationEvent: Flow<NavigationEvent>

    fun goBack()

    fun goToHome()

    fun goToRockets()
}