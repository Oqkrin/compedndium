package oqk.ananke.compedndium.core.data

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import kotlin.math.pow
import kotlin.math.sqrt

@Stable
class ScreenPhiUnits(
    w: Dp,
    h: Dp,
    wSizeClass: WindowWidthSizeClass,
    hSizeClass: WindowHeightSizeClass,
) {

    val isLandscape = w > h

    // w dimension phi divisions
    private val w0 by lazy { w }
    private val w1 by lazy { w0 * PHI_INV }
    private val w2 by lazy { w1 * PHI_INV }
    private val w3 by lazy { w2 * PHI_INV }
    private val w4 by lazy { w3 * PHI_INV }
    private val w5 by lazy { w4 * PHI_INV }
    private val w6 by lazy { w5 * PHI_INV }
    private val w7 by lazy { w6 * PHI_INV }
    private val w8 by lazy { w7 * PHI_INV }
    private val w9 by lazy { w8 * PHI_INV }

    // h dimension phi divisions
    private val h0 by lazy { h }
    private val h1 by lazy { h0 * PHI_INV }
    private val h2 by lazy { h1 * PHI_INV }
    private val h3 by lazy { h2 * PHI_INV }
    private val h4 by lazy { h3 * PHI_INV }
    private val h5 by lazy { h4 * PHI_INV }
    private val h6 by lazy { h5 * PHI_INV }
    private val h7 by lazy { h6 * PHI_INV }
    private val h8 by lazy { h7 * PHI_INV }
    private val h9 by lazy { h8 * PHI_INV }

    /** Padding calculations */
    fun calcPadding(size: Dp, multiplier: Float = PHI/100, addendum: Dp = 0.dp): Dp =
        addendum + (size * multiplier)

    /** Padding from the screen border */
    val screenWidthPadding = calcPadding(w, 0.015625f, 8.dp)

    /** Padding from the screen border */
    val screenHeightPadding = calcPadding(h, 0.015625f, 8.dp)

    /** Icon sizing function */
    fun iconSizeForContainer(containerWidth: Dp, containerHeight: Dp): Dp =
        min(containerWidth, containerHeight) * PHI_INV

    /** Icon sizing function */
    fun iconSizeForContainer(containerSize: Dp): Dp = containerSize * PHI_INV


    val phiOpacity: Float = PHI_INV

    fun wUnit(level: Int): Dp = when (level) {
        0 -> w0; 1 -> w1; 2 -> w2; 3 -> w3; 4 -> w4
        5 -> w5; 6 -> w6; 7 -> w7; 8 -> w8; 9 -> w9
        else -> (w0 / PHI.pow(level))
    }

    fun hUnit(level: Int): Dp = when (level) {
        0 -> h0; 1 -> h1; 2 -> h2; 3 -> h3; 4 -> h4
        5 -> h5; 6 -> h6; 7 -> h7; 8 -> h8; 9 -> h9
        else -> (h0 / PHI.pow(level))
    }

    fun wUnit(level: Float): Dp = (w0 / PHI.pow(level))

    fun hUnit(level: Float): Dp = (h0 / PHI.pow(level))

    fun minUnit(level: Int): Dp = if (isLandscape) hUnit(level) else wUnit(level)

    fun maxUnit(level: Int): Dp = if (isLandscape) wUnit(level) else hUnit(level)

    fun minUnit(level: Float): Dp = if (isLandscape) hUnit(level) else wUnit(level)

    fun maxUnit(level: Float): Dp = if (isLandscape) wUnit(level) else hUnit(level)

    companion object GoldenConst {
        val PHI = (1+ sqrt(5f))/2
        val PHI_INV = 1/PHI

        const val PHICORNERPERCENT: Int = 16
        const val CIRCLEPERCENT:  Int = 50
    }

}