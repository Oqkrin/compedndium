package oqk.ananke.compedndium.core.presentation.theme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Light theme: Aged parchment with warm, creamy tones and dark ink for text
private val AncientScrollLight = lightColorScheme(
    // Primary colors: Core theme for buttons, prominent UI
    primary = Color(0xFF8B7A5E), // Warm aged parchment brown, richer than original
    onPrimary = Color(0xFFF8EDE3), // Light cream for text/icons on primary
    primaryContainer = Color(0xFFE8DDD0), // Light parchment, unchanged
    onPrimaryContainer = Color(0xFF2D2520), // Dark ink, unchanged for contrast
    inversePrimary = Color(0xFFBFAE8F), // Lighter brown for inverse scenarios

    // Secondary colors: Subtle accents for secondary buttons, toggles
    secondary = Color(0xFF7A6F5D), // Weathered brown, slightly darker for contrast
    onSecondary = Color(0xFFF8EDE3), // Light cream for text/icons
    secondaryContainer = Color(0xFFF2E9E0), // Very light cream, unchanged
    onSecondaryContainer = Color(0xFF2D2520), // Dark ink, unchanged

    // Tertiary colors: Additional accents for nuanced hierarchy
    tertiary = Color(0xFF6B5C4A), // Muted leather brown for variety
    onTertiary = Color(0xFFF8EDE3), // Light cream
    tertiaryContainer = Color(0xFFE5D9C8), // Soft parchment shade
    onTertiaryContainer = Color(0xFF2D2520), // Dark ink

    // Surface colors: Backgrounds and cards
    surface = Color(0xFFF5E6DE), // Warmer parchment surface, moved from background
    onSurface = Color(0xFF2D2520), // Dark ink, unchanged
    surfaceVariant = Color(0xFFEDE4D5), // Slightly cooler parchment, moved from surface
    onSurfaceVariant = Color(0xFF4A4038), // Darker brown for secondary text/icons
    surfaceContainer = Color(0xFFD2C8B6), // Aged container, unchanged
    surfaceContainerLow = Color(0xFFF8ECE4), // Very light cream for low-elevation surfaces
    surfaceContainerLowest = Color(0xFFFFF7F0), // Brightest parchment for subtle layering
    surfaceContainerHigh = Color(0xFFE8DDD0), // Matches primaryContainer for consistency
    surfaceContainerHighest = Color(0xFFD9CFC1), // Slightly darker for high elevation
    surfaceBright = Color(0xFFF8ECE4), // Bright surface for dialogs/popups
    surfaceDim = Color(0xFFE1D7C9), // Dimmed surface for inactive states

    // Background: App background
    background = Color(0xFFF8ECE4), // Light cream, slightly brighter than original
    onBackground = Color(0xFF2D2520), // Dark ink, unchanged

    // Outline and borders
    outline = Color(0xFF8D867A), // Weathered brown, unchanged
    outlineVariant = Color(0xFFB3A99A), // Lighter outline for subtle dividers

    // Error colors: For alerts, errors
    error = Color(0xFFB00020), // Standard Material 3 error red
    onError = Color(0xFFFFFFFF), // White for text/icons
    errorContainer = Color(0xFFF9DEDC), // Light red container
    onErrorContainer = Color(0xFF410E0B), // Dark red text

    // Inverse and scrim
    inverseSurface = Color(0xFF4A4038), // Dark brown for inverse surfaces
    inverseOnSurface = Color(0xFFF8ECE4), // Light cream for text on inverse
    scrim = Color(0x80000000) // Semi-transparent black for modals
)

// Dark theme: Dark leather with parchment highlights
private val AncientScrollDark = darkColorScheme(
    // Primary colors
    primary = Color(0xFFBFAE8F), // Lighter parchment brown for prominence
    onPrimary = Color(0xFF2D2520), // Dark ink for contrast
    primaryContainer = Color(0xFF5D534A), // Dark leather, moved from primary
    onPrimaryContainer = Color(0xFFEDE4D5), // Light parchment, unchanged
    inversePrimary = Color(0xFF8B7A5E), // Matches light theme primary

    // Secondary colors
    secondary = Color(0xFFA69B8A), // Lighter weathered brown for contrast
    onSecondary = Color(0xFF2D2520), // Dark ink
    secondaryContainer = Color(0xFF3E3B39), // Very dark leather, unchanged
    onSecondaryContainer = Color(0xFFEDE4D5), // Light parchment, unchanged

    // Tertiary colors
    tertiary = Color(0xFF9B8A6F), // Soft leather brown for variety
    onTertiary = Color(0xFF2D2520), // Dark ink
    tertiaryContainer = Color(0xFF4A4038), // Darker leather shade
    onTertiaryContainer = Color(0xFFEDE4D5), // Light parchment

    // Surface colors
    surface = Color(0xFF2A2520), // Dark leather background, moved from background
    onSurface = Color(0xFFEDE4D5), // Light parchment text, unchanged
    surfaceVariant = Color(0xFF4F4A47), // Slightly lighter leather, moved from surface
    onSurfaceVariant = Color(0xFFD2C8B6), // Light parchment for secondary text, unchanged
    surfaceContainer = Color(0xFF3E3B39), // Matches secondaryContainer for consistency
    surfaceContainerLow = Color(0xFF2A2520), // Darkest leather for low elevation
    surfaceContainerLowest = Color(0xFF211C18), // Deepest background shade
    surfaceContainerHigh = Color(0xFF4A4038), // Slightly lighter for elevation
    surfaceContainerHighest = Color(0xFF5D534A), // Matches primaryContainer
    surfaceBright = Color(0xFF4F4A47), // Brighter surface for dialogs
    surfaceDim = Color(0xFF211C18), // Dimmed surface for inactive states

    // Background
    background = Color(0xFF211C18), // Deeper leather tone for depth
    onBackground = Color(0xFFEDE4D5), // Light parchment text, unchanged

    // Outline and borders
    outline = Color(0xFFA69B8A), // Lighter weathered brown for visibility
    outlineVariant = Color(0xFF6B5C4A), // Darker outline for dividers

    // Error colors
    error = Color(0xFFCF6679), // Standard Material 3 dark error red
    onError = Color(0xFF1C0B0E), // Dark for text/icons
    errorContainer = Color(0xFF8B2E3B), // Dark red container
    onErrorContainer = Color(0xFFF9DEDC), // Light red text

    // Inverse and scrim
    inverseSurface = Color(0xFFEDE4D5), // Light parchment for inverse
    inverseOnSurface = Color(0xFF2D2520), // Dark ink
    scrim = Color(0x80000000) // Semi-transparent black for modals
)

// Expose as public vals for use in your app
val AncientScrollLightColorScheme: ColorScheme = AncientScrollLight
val AncientScrollDarkColorScheme: ColorScheme = AncientScrollDark