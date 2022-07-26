package com.dominikwieczynski.issuetracker.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.dominikwieczynski.issuetracker.ui.theme.Shapes
import com.dominikwieczynski.issuetracker.ui.theme.Typography

private val DarkColorPalette = darkColorScheme(
   // primary = com.dominikwieczynski.issuetracker.ui.theme.Purple200,
   // secondary = com.dominikwieczynski.issuetracker.ui.theme.Purple700
)

private val LightColorPalette = lightColorScheme(
  //  primary = com.dominikwieczynski.issuetracker.ui.theme.Purple500,
 //   secondary = com.dominikwieczynski.issuetracker.ui.theme.Purple700

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun IssueTrackerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    /*
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )

     */
    MaterialTheme(
       colorScheme = lightColorScheme(),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}