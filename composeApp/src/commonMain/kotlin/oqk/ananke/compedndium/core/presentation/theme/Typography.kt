package oqk.ananke.compedndium.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable

@Composable
fun AppTypography() = Typography().run {
    val jacquard = platformFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = jacquard),
        displayMedium = displayMedium.copy(fontFamily = jacquard),
        displaySmall = displaySmall.copy(fontFamily = jacquard),
        headlineLarge = headlineLarge.copy(fontFamily = jacquard),
        headlineMedium = headlineMedium.copy(fontFamily = jacquard),
        headlineSmall = headlineSmall.copy(fontFamily = jacquard),
        titleLarge = titleLarge.copy(fontFamily = jacquard),
        titleMedium = titleMedium.copy(fontFamily = jacquard),
        titleSmall = titleSmall.copy(fontFamily = jacquard),
        bodyLarge = bodyLarge.copy(fontFamily = jacquard),
        bodyMedium = bodyMedium.copy(fontFamily = jacquard),
        bodySmall = bodySmall.copy(fontFamily = jacquard),
        labelLarge = labelLarge.copy(fontFamily = jacquard),
        labelMedium = labelMedium.copy(fontFamily = jacquard),
        labelSmall = labelSmall.copy(fontFamily = jacquard)
    )
}