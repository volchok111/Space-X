package com.volchok.space_x.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.volchok.space_x.app.model.BackNavigationEvent
import com.volchok.space_x.app.model.ForwardNavigationEvent
import com.volchok.space_x.app.model.Route
import com.volchok.space_x.app.presentation.MainViewModel
import com.volchok.space_x.feature.company.ui.CompanyScreen
import com.volchok.space_x.feature.details.ui.DetailsScreen
import com.volchok.space_x.feature.rockets.ui.RocketsScreen
import com.volchok.space_x.library.ui.SpaceXBottomBar
import com.volchok.space_x.ui.theme.SpaceXTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen() {
    val viewModel = getViewModel<MainViewModel>()

    MainScreenImpl(
        viewModel = viewModel,
        viewModel::onCompany,
        viewModel::onRockets
    )
}

@Composable
fun MainScreenImpl(
    viewModel: MainViewModel,
    onCompany: () -> Unit,
    onRockets: () -> Unit,
) {
    SpaceXTheme {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                SpaceXBottomBar(
                    onHome = { onCompany() },
                    onRockets = { onRockets() }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                NavigationEffect(
                    navController = navController,
                    viewModel = viewModel,
                    onNavigationEventConsumed = viewModel::onNavigationEventConsumed
                )

                Screens(
                    navController = navController,
                    modifier = Modifier
                        .weight(1f)
                        .background(MaterialTheme.colors.background)
                )
                //TODO implement internet connection state
            }
        }
    }
}

@Composable
private fun Screens(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Initial(),
        modifier = modifier
    ) {
        composable(Route.Company()) { CompanyScreen() }
        composable(Route.Details()) { DetailsScreen() }
        composable(Route.Rockets()) { RocketsScreen() }

    }
}

@Composable
private fun NavigationEffect(
    navController: NavHostController,
    viewModel: MainViewModel,
    onNavigationEventConsumed: () -> Unit
) {
    val state = viewModel.states.collectAsState()
    val navigationEvent = state.value.navigationEvent

    SideEffect {
        when (navigationEvent) {
            is BackNavigationEvent -> {
                navController.navigateUp()
                onNavigationEventConsumed()
            }

            is ForwardNavigationEvent -> {
                if (navController.currentDestination?.route != navigationEvent.route()) {
                    val navOptions = prepareNavOptions(navigationEvent)

                    navController.navigate(navigationEvent.route(), navOptions)
                    onNavigationEventConsumed()
                }
            }

            null -> Unit
        }
    }
}

private fun prepareNavOptions(navigationEvent: ForwardNavigationEvent): NavOptions? {
    return if (navigationEvent.clearBackStack) {
        NavOptions.Builder().also { navOptionsBuilder ->
            navigationEvent.clearBackStackRoute?.let {
                navOptionsBuilder.setPopUpTo(it(), false)
            } ?: navOptionsBuilder.setPopUpTo(0, false)
        }.build()
    } else {
        null
    }
}