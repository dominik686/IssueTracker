package com.dominikwieczynski.issuetracker.common.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import com.dominikwieczynski.issuetracker.R

@Composable
fun AmongUsLoadingAnimation(modifier: Modifier = Modifier, iterations: Int = LottieConstants.IterateForever)
{
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_among_us))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = iterations)


    LottieAnimation(modifier = modifier.wrapContentHeight(),
        composition = composition,
        progress =  {progress},
        contentScale = ContentScale.Crop)
        LoadingTextAnimation()

}

@Composable
private fun LoadingTextAnimation(modifier: Modifier = Modifier, iterations: Int = LottieConstants.IterateForever

){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_text_animation))
    val progress by animateLottieCompositionAsState(composition = composition,
        iterations = iterations)

    LottieAnimation(modifier = modifier.wrapContentHeight(),
        composition = composition,
        progress =  {progress},
        contentScale = ContentScale.Crop
        )
}