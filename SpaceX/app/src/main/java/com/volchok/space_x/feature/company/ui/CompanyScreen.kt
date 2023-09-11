package com.volchok.space_x.feature.company.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.volchok.space_x.R
import com.volchok.space_x.feature.company.presentation.CompanyViewModel
import com.volchok.space_x.library.ui.SpaceXColors
import com.volchok.space_x.library.ui.SpaceXDimensions
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeXXS
import com.volchok.space_x.library.ui.SpaceXLoadingDialog
import com.volchok.space_x.library.ui.SpaceXText
import org.koin.androidx.compose.getViewModel

@Composable
fun CompanyScreen() {
    val viewModel = getViewModel<CompanyViewModel>()
    val state = viewModel.states.collectAsState()

    CompanyScreenImpl(
        state = state.value
    )
}

@Composable
private fun CompanyScreenImpl(
    state: CompanyViewModel.State
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceXDimensions.sizeXS)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SpaceXText(
                text = state.company?.name.orEmpty(),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(SpaceXDimensions.sizeM))

        Column {
            CompanyCardItem(title = stringResource(id = R.string.company_screen_ceo), subtitle = state.company?.ceo.orEmpty())
            CompanyCardItem(title = stringResource(id = R.string.company_screen_coo), subtitle = state.company?.coo.orEmpty())
            CompanyCardItem(title = stringResource(id = R.string.company_screen_founded), subtitle = state.company?.founded.toString())
            CompanyCardItem(title = stringResource(id = R.string.company_screen_employees), subtitle = state.company?.employees.toString())
            CompanyCardItem(title = stringResource(id = R.string.company_screen_valuation), subtitle = state.company?.valuation.toString() + "$")
        }

    }
    if (state.loading) {
        SpaceXLoadingDialog(title = "")
    }
}

@Composable
private fun CompanyCardItem(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(SpaceXDimensions.sizeXS)) {
        Card(
            modifier = modifier
                .height(60.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            elevation = sizeXXS
        ) {
            Box(
                modifier = modifier
                    .background(SpaceXColors.chrome100)
                    .padding(start = SpaceXDimensions.sizeS, end = SpaceXDimensions.sizeS),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()

                ) {
                    SpaceXText(
                        text = title,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = SpaceXDimensions.sizeXS)
                    )
                    SpaceXText(
                        text = subtitle,
                        style = MaterialTheme.typography.h5,
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(SpaceXDimensions.sizeM))
}
