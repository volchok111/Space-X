package com.volchok.space_x.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.volchok.space_x.R
import com.volchok.space_x.feature.home.presentation.HomeViewModel
import com.volchok.space_x.library.ui.SpaceXColors
import com.volchok.space_x.library.ui.SpaceXColors.black
import com.volchok.space_x.library.ui.SpaceXColors.chrome300
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeL
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeS
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeXL
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeXS
import com.volchok.space_x.library.ui.SpaceXIcon
import com.volchok.space_x.library.ui.SpaceXLoadingDialog
import com.volchok.space_x.library.ui.SpaceXText
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    val state = viewModel.states.collectAsState()

    HomeScreenImpl(
        state = state.value
    )
}

@Composable
private fun HomeScreenImpl(
    state: HomeViewModel.State,
    onItem: (String) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(sizeS)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxWidth()
        ) {
            SpaceXText(
                text = stringResource(id = R.string.home_screen_title),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = black
            )
        }
        Spacer(modifier = Modifier.height(sizeS))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(SpaceXColors.white)
                    .padding(start = sizeS, end = sizeS)
            ) {
                LazyColumn {
                    itemsIndexed(state.rockets) { index, item ->
                        if (index != 0 && index != 4) {
                            Divider(color = SpaceXColors.chrome100, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(sizeXS))
                        RocketListItem(
                            item = item,
                            modifier = Modifier.clickable { item.id?.let { onItem(it) } }
                        )
                        Spacer(modifier = Modifier.height(sizeXS))
                    }
                }
            }
        }
    }

    if (state.loading) {
        SpaceXLoadingDialog(title = "")
    }
}

@Composable
private fun RocketListItem(
    item: HomeViewModel.State.RocketItem,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SpaceXIcon(
            icon = R.drawable.rocket,
            contentDescription = null,
            tint = black,
            modifier = Modifier
                .size(sizeL)
                .padding(end = sizeXS)
                .align(Alignment.CenterVertically)
        )

        Column(modifier = Modifier.weight(1f)) {
            item.name?.let {
                SpaceXText(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    color = black,
                    fontWeight = FontWeight.Bold
                )
            }
            SpaceXText(
                text = "${stringResource(id = R.string.home_screen_first_flight)} ${item.first_flight}",
                style = MaterialTheme.typography.subtitle2,
                color = chrome300
            )
        }
        SpaceXIcon(
            icon = R.drawable.icon_navigate_next,
            contentDescription = null,
            tint = chrome300,
            modifier = Modifier
                .size(sizeXL)
                .align(Alignment.CenterVertically)
        )
    }
}