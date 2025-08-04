package oqk.ananke.compedndium.core.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import compedndium.composeapp.generated.resources.Res
import compedndium.composeapp.generated.resources.Jacquard12_Regular
import compedndium.composeapp.generated.resources.Jacquard24_Regular
import org.jetbrains.compose.resources.Font

@Composable
actual fun platformFontFamily(): FontFamily = FontFamily(
    Font(Res.font.Jacquard12_Regular, FontWeight.Normal),
    Font(Res.font.Jacquard24_Regular, FontWeight.Bold)
)