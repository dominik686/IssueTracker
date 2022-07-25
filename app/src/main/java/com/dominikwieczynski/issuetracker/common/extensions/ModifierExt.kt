package com.dominikwieczynski.issuetracker.common.extensions

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun Modifier.textButtonModifier(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp, 16.dp, 0.dp)
}

fun Modifier.basicButtonModifier(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 8.dp)
}
fun Modifier.fieldModifier(): Modifier {
    return this.fillMaxWidth().padding(16.dp, 4.dp)
}

fun Modifier.bannerModifier(): Modifier{
    return this.padding(16.dp, 16.dp)
}
fun Modifier.spacer(): Modifier {
    return this.fillMaxWidth().padding(12.dp)
}

fun Modifier.smallSpacer(): Modifier {
    return this.fillMaxWidth().height(8.dp)
}
fun Modifier.signUpScreenSpacer(): Modifier{
    return this.fillMaxWidth().padding(70.dp)
}

