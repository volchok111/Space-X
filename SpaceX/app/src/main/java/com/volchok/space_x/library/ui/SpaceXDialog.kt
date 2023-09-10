package com.volchok.space_x.library.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.volchok.space_x.R
import com.volchok.space_x.library.ui.SpaceXColors.black
import com.volchok.space_x.library.ui.SpaceXColors.white
import com.volchok.space_x.library.ui.SpaceXColors.yellow
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeM
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeS
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeXS
import com.volchok.space_x.library.ui.SpaceXDimensions.sizeXXS

@Composable
fun SpaceXAlertDialog(
    title: String,
    onDismiss: () -> Unit,
    positiveButtonText: String,
    modifier: Modifier = Modifier,
    onPositiveButtonClick: () -> Unit = onDismiss,
    message: String? = null,
    negativeButtonText: String? = null,
    onNegativeButtonClick: (() -> Unit) = onDismiss,
    neutralButtonText: String? = null,
    onNeutralButtonClick: (() -> Unit) = onDismiss,
    dialogProperties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = dialogProperties,

        ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(sizeXXS),
            color = white,
            contentColor = black
        ) {
            Column(
                modifier = Modifier.padding(sizeXS)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(sizeS)
                )
                if (!message.isNullOrEmpty()) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(sizeS)
                    )
                }
                Spacer(modifier = Modifier.height(sizeXS))
                Row {
                    neutralButtonText?.let {
                        SpaceXActionButton(
                            text = it,
                            onClick = onNeutralButtonClick
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    negativeButtonText?.let {
                        SpaceXActionButton(
                            text = it,
                            onClick = onNegativeButtonClick
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = sizeXXS),
                        contentAlignment = BottomEnd
                    ) {
                        SpaceXText(
                            text = positiveButtonText,
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier
                                .clickable { onPositiveButtonClick() }
                                .padding(end = sizeXS),
                            color = yellow,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(sizeXXS))
            }
        }
    }
}

@Composable
fun SpaceXLoadingDialog(
    title: String,
    modifier: Modifier = Modifier,
    text: String? = null,
) {

    val isLoading by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.rocket))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isLoading,
        iterations = 1000
    )

    Surface(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(sizeS),
        color = white,
        contentColor = black
    ) {
        Column(
            modifier = Modifier.padding(sizeM)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h2
            )
            if (!text.isNullOrEmpty()) {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = SpaceXDimensions.sizeL)
                )
            }
            Spacer(modifier = Modifier.height(sizeM))
            LottieAnimation(
                composition = composition,
                progress = { progress })
            Spacer(modifier = Modifier.height(sizeXXS))
        }
    }
}