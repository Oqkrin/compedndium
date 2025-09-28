package oqk.ananke.compedndium.core.domain

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.*

enum class WindowSizes(
    val displayName: String,
    val widthSizeClass: WindowWidthSizeClass,
    val heightSizeClass: WindowHeightSizeClass,
    val minWidth: Dp,
    val minHeight: Dp
) {
    INITIALIZING(
        "Loading",
        WindowWidthSizeClass.COMPACT,
        WindowHeightSizeClass.COMPACT,
        0.dp,
        0.dp
    ),
    PHONE_PORTRAIT(
        "Phone Portrait",
        WindowWidthSizeClass.COMPACT,
        WindowHeightSizeClass.MEDIUM,  // Example; actual match allows MEDIUM or EXPANDED
        360.dp,
        800.dp
    ),
    PHONE_LANDSCAPE(
        "Phone Landscape",
        WindowWidthSizeClass.MEDIUM,
        WindowHeightSizeClass.COMPACT,  // Example; actual match allows MEDIUM or EXPANDED width
        800.dp,
        360.dp
    ),
    TABLET_PORTRAIT(
        "Tablet Portrait",
        WindowWidthSizeClass.MEDIUM,
        WindowHeightSizeClass.EXPANDED,
        600.dp,
        1280.dp
    ),
    TABLET_LANDSCAPE(
        "Tablet Landscape",
        WindowWidthSizeClass.EXPANDED,
        WindowHeightSizeClass.MEDIUM,
        1280.dp,
        800.dp
    ),
    DESKTOP(
        "Desktop",
        WindowWidthSizeClass.EXPANDED,
        WindowHeightSizeClass.EXPANDED,
        1920.dp,
        1080.dp
    ),
    // Added for completeness; adjust as needed
    SMALL_SCREEN(
        "Small Screen",
        WindowWidthSizeClass.COMPACT,
        WindowHeightSizeClass.COMPACT,
        320.dp,
        320.dp
    ),
    SQUARE_MEDIUM(
        "Square Medium",
        WindowWidthSizeClass.MEDIUM,
        WindowHeightSizeClass.MEDIUM,
        700.dp,
        700.dp
    ),
    WIDE_SHORT(
        "Wide Short",
        WindowWidthSizeClass.EXPANDED,
        WindowHeightSizeClass.COMPACT,
        1000.dp,
        400.dp
    );

    companion object {
        fun match(widthClass: WindowWidthSizeClass, heightClass: WindowHeightSizeClass): WindowSizes {
            return when {
                widthClass == WindowWidthSizeClass.COMPACT && heightClass == WindowHeightSizeClass.COMPACT -> SMALL_SCREEN
                widthClass == WindowWidthSizeClass.COMPACT -> PHONE_PORTRAIT  // Covers MEDIUM or EXPANDED height (tall phones)
                heightClass == WindowHeightSizeClass.COMPACT -> PHONE_LANDSCAPE  // Covers MEDIUM or EXPANDED width (large phones in landscape)
                widthClass == WindowWidthSizeClass.MEDIUM && heightClass == WindowHeightSizeClass.MEDIUM -> SQUARE_MEDIUM
                widthClass == WindowWidthSizeClass.MEDIUM -> TABLET_PORTRAIT  // MEDIUM width, EXPANDED height
                heightClass == WindowHeightSizeClass.MEDIUM -> TABLET_LANDSCAPE  // EXPANDED width, MEDIUM height
                else -> DESKTOP  // EXPANDED width and height
            }
        }
    }
}