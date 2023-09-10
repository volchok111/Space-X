package com.volchok.space_x.library.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.volchok.space_x.R
import com.volchok.space_x.library.ui.SpaceXColors.black

@Composable
fun SpaceXBottomBar(
    onHome: () -> Unit,
    onRockets: () -> Unit,
) {
    BottomNavigation(
        backgroundColor = SpaceXColors.yellow
    ) {
        BottomBarItem(
            icon = R.drawable.home,
            selected = false,
            onClick = { onHome() },
            modifier = Modifier
                .size(SpaceXDimensions.sizeL)
        )
        BottomBarItem(
            icon = R.drawable.rocket,
            selected = false,
            onClick = { onRockets() },
            modifier = Modifier
                .size(SpaceXDimensions.sizeM)
        )
    }
}

@Composable
private fun RowScope.BottomBarItem(
    icon: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    BottomNavigationItem(
        selected = selected,
        onClick = { onClick() },
        icon = {
            SpaceXIcon(
                icon = icon,
                contentDescription = null,
                tint = black,
                modifier = modifier
            )
        }
    )
}