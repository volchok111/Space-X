package com.volchok.space_x.feature.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.volchok.space_x.R
import com.volchok.space_x.feature.details.presentation.DetailsViewModel
import com.volchok.space_x.library.ui.SpaceXColors.black
import com.volchok.space_x.library.ui.SpaceXColors.chrome50
import com.volchok.space_x.library.ui.SpaceXColors.yellow
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeL
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeM
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeS
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeXS
import com.volchok.space_x.library.ui.SpaceXIcon
import com.volchok.space_x.library.ui.SpaceXLoadingDialog
import com.volchok.space_x.library.ui.SpaceXText
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen() {
    val viewModel = getViewModel<DetailsViewModel>()
    val state = viewModel.states.collectAsState()

    DetailsScreenImpl(
        state = state.value
    )
}

@Composable
private fun DetailsScreenImpl(
    state: DetailsViewModel.State
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(sizeS)
            .verticalScroll(rememberScrollState())
    ) {

        SpaceXText(
            text = stringResource(id = R.string.details_screen_overview),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(sizeS))

        SpaceXText(
            text = state.rocket?.description.orEmpty(),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(sizeS))

        SpaceXText(
            text = stringResource(id = R.string.details_screen_parameters),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(sizeS))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ParametersItem(
                title = state.rocket?.height?.meters.toString() + stringResource(id = R.string.details_screen_m),
                subtitle = stringResource(id = R.string.details_screen_parameters_height)
            )
            ParametersItem(
                title = state.rocket?.diameter?.meters.toString() + stringResource(id = R.string.details_screen_m),
                subtitle = stringResource(id = R.string.details_screen_parameters_diameter)
            )
            ParametersItem(
                title = (state.rocket?.mass?.kg?.div(1000)).toString() + stringResource(id = R.string.details_screen_t),
                subtitle = stringResource(id = R.string.details_screen_parameters_mass)
            )
        }

        Spacer(modifier = Modifier.height(sizeM))

        RocketInfoCard(
            title = stringResource(id = R.string.details_screen_first_stage),
            reusable = state.rocket?.first_stage?.reusable.let {
                if (it == true) stringResource(id = R.string.details_screen_reusable) else stringResource(
                    id = R.string.details_screen_not_reusable
                )
            },
            enginesCount = state.rocket?.first_stage?.engines.toString(),
            fuel = state.rocket?.first_stage?.fuel_amount_tons.toString(),
            seconds = state.rocket?.first_stage?.burn_time_sec.toString()
        )
        Spacer(modifier = Modifier.height(sizeS))

        RocketInfoCard(
            title = stringResource(id = R.string.details_screen_second_stage),
            reusable = state.rocket?.second_stage?.reusable.let {
                if (it == true) stringResource(id = R.string.details_screen_reusable) else stringResource(
                    id = R.string.details_screen_not_reusable
                )
            },
            enginesCount = state.rocket?.second_stage?.engines.toString(),
            fuel = state.rocket?.second_stage?.fuel_amount_tons.toString(),
            seconds = state.rocket?.second_stage?.burn_time_sec.toString()
        )
        Spacer(modifier = Modifier.height(sizeM))

        SpaceXText(
            text = stringResource(id = R.string.details_screen_photos),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(sizeS))
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(state.rocket?.flickr_images?.get(0)),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(sizeS))

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(state.rocket?.flickr_images?.get(1)),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(sizeL))
    }

    if (state.loading) {
        SpaceXLoadingDialog(title = "")
    }
}

@Composable
private fun ParametersItem(
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier
            .size(112.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = yellow
    ) {
        Column(
            modifier = Modifier
                .padding(sizeS),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SpaceXText(
                text = title,
                style = MaterialTheme.typography.h5,
                color = black,
                fontWeight = FontWeight.Bold,
                fontSize = 23.sp,
                maxLines = 1,

                )
            Spacer(modifier = Modifier.height(sizeXS))

            SpaceXText(
                text = subtitle,
                style = MaterialTheme.typography.body1,
                color = black
            )
        }
    }
}

@Composable
private fun RocketInfoCard(
    title: String,
    enginesCount: String,
    fuel: String,
    seconds: String,
    reusable: String
) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = chrome50
    ) {
        Column(
            modifier = Modifier
                .padding(sizeS)
        ) {

            SpaceXText(text = title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = R.drawable.reusable,
                value = reusable
            )
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = R.drawable.engine,
                value = "$enginesCount ${stringResource(id = R.string.details_screen_engines)}"
            )
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = R.drawable.fuel,
                value = "$fuel ${stringResource(id = R.string.details_screen_tons)}"
            )
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = R.drawable.burn,
                value = "$seconds ${stringResource(id = R.string.details_screen_seconds)}"
            )
        }
    }
}

@Composable
private fun RocketInfoItem(
    icon: Int,
    value: String
) {
    Row(horizontalArrangement = Arrangement.Center) {
        SpaceXIcon(
            icon = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(end = sizeXS)
                .size(sizeM),
            tint = black
        )
        SpaceXText(text = value, style = MaterialTheme.typography.body1)
    }
}
