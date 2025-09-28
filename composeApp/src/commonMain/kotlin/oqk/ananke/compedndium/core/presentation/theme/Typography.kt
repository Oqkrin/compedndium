package oqk.ananke.compedndium.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import compedndium.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun appTypography(): Typography {
    val customFontFamily = FontFamily(
        Font(Res.font.Junicode, FontWeight.Normal),
        Font(Res.font.Junicode_Bold, FontWeight.Bold),
        Font(Res.font.Junicode_Italic, FontWeight.Normal, style = FontStyle.Italic),
        Font(Res.font.Junicode_BoldItalic, FontWeight.Bold, style = FontStyle.Italic),
        Font(Res.font.Junicode_BoldCondensed, FontWeight.SemiBold),
        Font(Res.font.Junicode_BoldItalicCondensed, FontWeight.SemiBold, style = FontStyle.Italic),
        Font(Res.font.Junicode_ItalicCondensed, FontWeight.Light, style = FontStyle.Italic),
        Font(Res.font.Junicode_RegularCondensed, FontWeight.Thin)
    )

    // Create a default Typography instance
    val defaultTypography = Typography()

    // Apply customFontFamily to all styles
    return Typography(
        displayLarge = defaultTypography.displayLarge.copy(fontFamily = customFontFamily),
        displayMedium = defaultTypography.displayMedium.copy(fontFamily = customFontFamily),
        displaySmall = defaultTypography.displaySmall.copy(fontFamily = customFontFamily),
        headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = customFontFamily),
        headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = customFontFamily),
        headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = customFontFamily),
        titleLarge = defaultTypography.titleLarge.copy(fontFamily = customFontFamily),
        titleMedium = defaultTypography.titleMedium.copy(fontFamily = customFontFamily),
        titleSmall = defaultTypography.titleSmall.copy(fontFamily = customFontFamily),
        bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = customFontFamily),
        bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = customFontFamily),
        bodySmall = defaultTypography.bodySmall.copy(fontFamily = customFontFamily),
        labelLarge = defaultTypography.labelLarge.copy(fontFamily = customFontFamily),
        labelMedium = defaultTypography.labelMedium.copy(fontFamily = customFontFamily),
        labelSmall = defaultTypography.labelSmall.copy(fontFamily = customFontFamily)
    )
}