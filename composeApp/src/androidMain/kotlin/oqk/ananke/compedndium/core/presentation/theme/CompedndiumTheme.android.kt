package oqk.ananke.compedndium.core.presentation.theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun getDynamicColorScheme(): Pair<ColorScheme?, ColorScheme?> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        dynamicLightColorScheme(LocalContext.current) to dynamicDarkColorScheme(LocalContext.current)
        else
            null to null
}