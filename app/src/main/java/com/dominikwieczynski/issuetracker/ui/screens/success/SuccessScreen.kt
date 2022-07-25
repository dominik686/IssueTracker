package com.dominikwieczynski.issuetracker.ui.screens.success

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dominikwieczynski.issuetracker.R
import com.dominikwieczynski.issuetracker.common.snackbar.SnackbarManager

@Composable
fun SuccessScreen(popUpTo: (String) -> Unit, @StringRes successMessage : Int, viewModel: SuccessViewModel = hiltViewModel())
{
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.success_animation))
        val progress  by animateLottieCompositionAsState(composition = composition)
        if(progress.equals(1F))
        {
            viewModel.onAnimationFinish(popUpTo)
        }
       // Text(text = stringResource(successMessage), style = MaterialTheme.typography.h5)
        LottieAnimation(composition = composition, progress = {progress})

        SnackbarManager.showMessage(successMessage)
    }

}