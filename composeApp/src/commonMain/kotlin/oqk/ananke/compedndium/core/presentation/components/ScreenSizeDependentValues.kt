package oqk.ananke.compedndium.core.presentation.components

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import kotlin.math.sqrt

/**
 * Centralized dimensions class for responsive UI
 * All proportional dimensions are calculated once in App.kt and passed down to components
 */
@Stable
data class ScreenSizeDependentValues(

    // Screen properties
    val screenWidth: Dp,
    val screenHeight: Dp,
    val isLandscape: Boolean = screenWidth > screenHeight,
    val minSize: Dp = min(screenWidth, screenHeight),
    val maxSize: Dp = max(screenWidth, screenHeight),

    val phi: Float = (1+ sqrt(5.0f))/2,

    //screenPadding
    val bottomPadding: Dp = responsivePadding(screenHeight),
    val topPadding: Dp = bottomPadding,
    val endPadding: Dp = responsivePadding(screenWidth),
    val startPadding: Dp = endPadding,

    // Fab
    val fabOpacity: Float = 0.75f,

    val fabBarSize: Dp = minSize - if (isLandscape) (topPadding+bottomPadding) else (startPadding+endPadding),
    val fabBarUnit: Dp = fabBarSize/7,
    val fabTabZoneSize: Dp = fabBarUnit*4,
    val fabZonesSize: Dp = (fabBarSize-fabTabZoneSize)/2,

    val fabBaseSize: Dp = fabZonesSize/phi,
    val cornerRadius: Dp = fabBaseSize * (phi/10),

    val fabLargeTabSide: Dp = (fabTabZoneSize/(8.5f/phi)),
    val fabSmallTabSide: Dp = fabLargeTabSide/phi,

    val iconSize: Dp = fabBaseSize/phi,
    val smallIconSize: Dp = fabSmallTabSide/phi
) {
    fun calcPadding(size: Dp): Dp {
        return responsivePadding(size, phi/100) - 8.dp
    }
}

fun responsivePadding(size: Dp, paddinVar: Float = 0.015625f): Dp {
    return 8.dp + (paddinVar * size.value).dp
}
