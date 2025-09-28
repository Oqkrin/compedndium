package oqk.ananke.compedndium.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun CompedndiumTheme(
    isDynamicThemeOn : Boolean = true,
    isDarkThemeOn: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val dynamicColorScheme = if (isDynamicThemeOn) getDynamicColorScheme() else null to null

    val colorScheme = if (isDarkThemeOn) {
        dynamicColorScheme.second ?: AncientScrollDarkColorScheme
    } else {
        dynamicColorScheme.first ?: AncientScrollLightColorScheme
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = appTypography(),
        content = content
    )
}

@Composable
expect fun getDynamicColorScheme(): Pair<ColorScheme?, ColorScheme?>

