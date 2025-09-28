package oqk.ananke.compedndium.core.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Refined Vintage Scroll Light Palette
private val VintageScrollLight = lightColorScheme(
    primary = Color(0xFFb96f2c), // Warm orange-brown
    onPrimary = Color(0xFFcaa982), // Light tan
    primaryContainer = Color(0xFFcaa982), // Light tan container
    onPrimaryContainer = Color(0xFF4a473a), // Dark olive
    secondary = Color(0xFF8b9a3b), // Olive green
    onSecondary = Color(0xFFcaa982), // Light tan
    secondaryContainer = Color(0xFF629cc5), // Soft blue
    onSecondaryContainer = Color(0xFF36445a), // Dark blue-gray
    surface = Color(0xFFcaa982), // Light tan surface
    onSurface = Color(0xFF4a473a), // Dark olive text
    onSurfaceVariant = Color(0xFF774024), // Medium brown
    surfaceContainer = Color(0xFFaa8085), // Dusty rose
    outline = Color(0xFF707068) // Gray outline
)

// Refined Vintage Scroll Dark Palette
private val VintageScrollDark = darkColorScheme(
    primary = Color(0xFF8b9a3b), // Olive green
    onPrimary = Color(0xFF182029), // Very dark blue
    primaryContainer = Color(0xFF4a473a), // Dark olive
    onPrimaryContainer = Color(0xFFcaa982), // Light tan
    secondary = Color(0xFF629cc5), // Soft blue
    onSecondary = Color(0xFF182029), // Very dark blue
    secondaryContainer = Color(0xFF36445a), // Dark blue-gray
    onSecondaryContainer = Color(0xFF629cc5), // Soft blue
    surface = Color(0xFF33333a), // Dark gray
    onSurface = Color(0xFFcaa982), // Light tan
    onSurfaceVariant = Color(0xFFaa8085), // Dusty rose
    surfaceContainer = Color(0xFF2c403c), // Dark teal
    outline = Color(0xFF67628a) // Purple-gray outline
)

val VintageScrollLightColorScheme = VintageScrollLight
val VintageScrollDarkColorScheme = VintageScrollDark