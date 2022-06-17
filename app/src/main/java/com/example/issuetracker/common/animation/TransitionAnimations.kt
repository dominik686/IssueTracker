package com.example.issuetracker.common.animation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object TransitionAnimations {
    val defaultExitTransition = slideOutHorizontally(targetOffsetX = {300},
        animationSpec = tween(durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + fadeOut(animationSpec = tween(300))
    val defaultPopEnterAnimation = slideInHorizontally(initialOffsetX = {-300},
        animationSpec = tween(durationMillis = 300,
            easing = FastOutSlowInEasing)) + fadeIn(animationSpec = tween(durationMillis = 300))
    val defaultEnterTransition = slideInHorizontally(initialOffsetX = {-300},
        animationSpec = tween(durationMillis = 300,
            easing = FastOutSlowInEasing)) + fadeIn(animationSpec = tween(300))

    val defaultPopExitTransition = slideOutHorizontally(targetOffsetX = {-300},
        animationSpec = tween(durationMillis = 300,
            easing = FastOutSlowInEasing)) + fadeOut(animationSpec = tween(durationMillis = 300))
}