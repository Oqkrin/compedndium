package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import oqk.ananke.compedndium.core.data.ScreenPhiUnits
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Favorites(
    modifier: Modifier,
    values: ScreenPhiUnits
) {
    // Define Animatables for position, scales, rotation, and opacity
    val cardOffset = remember { Animatable(0f) }
    val cardScaleX = remember { Animatable(1f) }
    val cardScaleY = remember { Animatable(1f) }
    val cardRotation = remember { Animatable(0f) }
    val cardOpacity = remember { Animatable(1f) }
    val coroutineScope = rememberCoroutineScope()

    // Use wUnit(0) to get the full screen width as requested
    val screenWidth = values.wUnit(0).value
    val travelTimeOut = 250 // Slightly increased for better visibility
    val travelTimeIn = travelTimeOut * 2 // Slower entry for more visibility

    // Easing for outgoing animations: slow start, accelerate to fast end for smooth takeoff and quick exit
    val outSpec = tween<Float>(travelTimeOut, easing = EaseInCubic)

    // Spring spec for incoming animations: adjusted for more pronounced bounce and smoother settling
    val bouncySpring = spring<Float>(
        dampingRatio = Spring.DampingRatioLowBouncy, // Lower damping for more bounces
        stiffness = Spring.StiffnessLow // Lower stiffness for slower, more visible oscillation
    )

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Left Arrow Button (previous)
        ArrowButton(
            size = values.minUnit(5.5f),
            rotation = 360,
            onClick = {
                coroutineScope.launch {
                    // Animate out to the left: widen, squish height, rotate slightly, fade a bit with easing
                    launch { cardScaleX.animateTo(1.3f, animationSpec = outSpec) }
                    launch { cardScaleY.animateTo(0.8f, animationSpec = outSpec) }
                    launch { cardRotation.animateTo(10f, animationSpec = outSpec) }
                    launch { cardOpacity.animateTo(0.6f, animationSpec = outSpec) }
                    cardOffset.animateTo(-screenWidth, animationSpec = outSpec)

                    // Snap to the right side for portal effect, reset for incoming animation
                    cardOffset.snapTo(screenWidth)
                    cardScaleX.snapTo(1.3f)
                    cardScaleY.snapTo(0.8f)
                    cardRotation.snapTo(-10f)
                    cardOpacity.snapTo(0.6f)

                    // Animate back to center from right: restore scales with enhanced bounce, rotate back, fade in
                    launch { cardScaleX.animateTo(1f, animationSpec = bouncySpring) }
                    launch { cardScaleY.animateTo(1f, animationSpec = bouncySpring) }
                    launch { cardRotation.animateTo(0f, animationSpec = bouncySpring) }
                    launch { cardOpacity.animateTo(1f, animationSpec = bouncySpring) }
                    cardOffset.animateTo(0f, animationSpec = bouncySpring)
                }
            }
        )

        // EntityCard with dynamic offset, scale, rotation, and opacity
        EntityCard(
            Entity(0.toString(), "Olivander", listOf("OC", "DND", "Warlock", "Frenzy"), EntityType.PC),
            modifier = Modifier
                .offset(x = cardOffset.value.dp)
                .scale(scaleX = cardScaleX.value, scaleY = cardScaleY.value)
                .rotate(cardRotation.value)
                .alpha(cardOpacity.value)
                .padding(horizontal = values.minUnit(5)),
            values = values,
            layout = EntityCardLayout.CARD
        )

        // Right Arrow Button (next)
        ArrowButton(
            size = values.minUnit(5.5f),
            rotation = 180,
            onClick = {
                coroutineScope.launch {
                    // Animate out to the right: widen, squish height, rotate slightly, fade a bit with easing
                    launch { cardScaleX.animateTo(1.3f, animationSpec = outSpec) }
                    launch { cardScaleY.animateTo(0.8f, animationSpec = outSpec) }
                    launch { cardRotation.animateTo(-10f, animationSpec = outSpec) }
                    launch { cardOpacity.animateTo(0.6f, animationSpec = outSpec) }
                    cardOffset.animateTo(screenWidth, animationSpec = outSpec)

                    // Snap to the left side for portal effect, reset for incoming animation
                    cardOffset.snapTo(-screenWidth)
                    cardScaleX.snapTo(1.3f)
                    cardScaleY.snapTo(0.8f)
                    cardRotation.snapTo(10f)
                    cardOpacity.snapTo(0.6f)

                    // Animate back to center from left: restore scales with enhanced bounce, rotate back, fade in
                    launch { cardScaleX.animateTo(1f, animationSpec = bouncySpring) }
                    launch { cardScaleY.animateTo(1f, animationSpec = bouncySpring) }
                    launch { cardRotation.animateTo(0f, animationSpec = bouncySpring) }
                    launch { cardOpacity.animateTo(1f, animationSpec = bouncySpring) }
                    cardOffset.animateTo(0f, animationSpec = bouncySpring)
                }
            }
        )
    }
}

// Stubs for the other components to make the code complete and runnable.
@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ArrowButton(
    size: Dp,
    rotation: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        if (isPressed) ScreenPhiUnits.PHI else 1.0f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy),
        label = "arrowScale"
    )

    Box(
        modifier = modifier
            .size(size)
            .scale(scale)
            .shadow(12.dp, MaterialShapes.Arrow.toShape(rotation))
            .clip(MaterialShapes.Arrow.toShape(rotation))
            .background(color = MaterialTheme.colorScheme.tertiary)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                onClick = onClick
            )
    )
}