package com.volchok.space_x.feature.company.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.volchok.space_x.feature.company.presentation.CompanyViewModel
import com.volchok.space_x.library.ui.SpaceXDimensions
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
            .padding(SpaceXDimensions.sizeS)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            SpaceXText(
                text = state.company?.name.orEmpty(),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(SpaceXDimensions.sizeS))
        //TODO: Implement horizontal pager with company info

    }
    if (state.loading) {
        SpaceXLoadingDialog(title = "")
    }
}
