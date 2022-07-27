package com.dominikwieczynski.issuetracker.common.composables

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import com.dominikwieczynski.issuetracker.R

@Composable
fun AmongUsLoadingAnimation(modifier: Modifier = Modifier, iterations: Int = LottieConstants.IterateForever, color: Color = MaterialTheme.colors.primary)
{
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_among_us))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = iterations)
    val dynamicProperties = rememberLottieDynamicProperties(properties = arrayOf(
        rememberLottieDynamicProperty(
        property = LottieProperty.COLOR,
        value = MaterialTheme.colors.primary,
        keyPath = arrayOf(
            "H2",
            "Shape 1",
            "Fill 1"
        )
    )
    )
    )
    LottieAnimation(modifier = modifier,
        composition = composition,
        progress =  {progress},
    dynamicProperties = dynamicProperties)


}