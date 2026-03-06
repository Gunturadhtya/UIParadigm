package com.experiment.uiparadigm.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Blue700,
    background = androidx.compose.ui.graphics.Color(0xFFF5F5F5)
)

@Composable
fun UIParadigmTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = LightColors, content = content)
}
