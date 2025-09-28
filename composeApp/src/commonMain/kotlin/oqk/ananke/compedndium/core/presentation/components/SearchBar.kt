package oqk.ananke.compedndium.core.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import oqk.ananke.compedndium.core.data.ScreenPhiUnits
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListAction
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListState
import compedndium.composeapp.generated.resources.Res
import compedndium.composeapp.generated.resources.search_toggle
import org.jetbrains.compose.resources.stringResource

// Define animation specs for reusability
private val BouncySpringFloat: FiniteAnimationSpec<Float> = SpringSpec(
    dampingRatio = Spring.DampingRatioLowBouncy,
    stiffness = Spring.StiffnessVeryLow
)

private val BouncySpringInt: FiniteAnimationSpec<IntOffset> = SpringSpec(
    dampingRatio = Spring.DampingRatioLowBouncy,
    stiffness = Spring.StiffnessVeryLow
)

private val PressSpring = spring<Float>(
    dampingRatio = Spring.DampingRatioHighBouncy,
    stiffness = Spring.StiffnessMedium
)

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    state: EntityListState,
    onAction: (EntityListAction) -> Unit,
    values: ScreenPhiUnits
) {
    Box(
        modifier = modifier.height(values.minUnit(5))
    ) {
        Row (Modifier.align(Alignment.CenterEnd)) {

            SearchTextField(
                isSearching = state.isSearching,
                isSearchOn = state.isSearchOn,
                query = state.searchQuery,
                onQueryChange = { onAction(EntityListAction.OnSearchQueryChange(it)) },
                values = values,
            )

            Spacer(Modifier.size(values.minUnit(5) + 3.dp))
        }

        SearchToggleButton(
            isSearchOn = state.isSearchOn,
            onClick = { onAction(EntityListAction.OnSearchToggle) },
            values = values,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}

// Separate composable for the animated text field
@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchTextField(
    isSearching: Boolean,
    isSearchOn: Boolean,
    query: String,
    onQueryChange: (String) -> Unit,
    values: ScreenPhiUnits,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    AnimatedVisibility(
        visible = isSearchOn,
        enter = slideInHorizontally(
            animationSpec = BouncySpringInt,
            initialOffsetX = { it / 2 }
        ) + fadeIn(tween(300)) + scaleIn(BouncySpringFloat),
        exit = slideOutHorizontally(
            animationSpec = BouncySpringInt,
            targetOffsetX = { it / 2 }
        ) + fadeOut(tween(300)) + scaleOut(BouncySpringFloat)
    ) {
        val textFieldElevation by animateDpAsState(
            targetValue = if (isSearchOn) 8.dp else 0.dp,
            animationSpec = tween(300)
        )

        val TextFieldShape = RoundedCornerShape(
            topStartPercent = ScreenPhiUnits.PHICORNERPERCENT,
            bottomStartPercent = ScreenPhiUnits.PHICORNERPERCENT,
            topEndPercent = ScreenPhiUnits.PHICORNERPERCENT / 2,
            bottomEndPercent = ScreenPhiUnits.PHICORNERPERCENT / 2
        )

        Box(
            modifier = modifier
                .height(values.minUnit(5))
                .widthIn(min = values.minUnit(4))
                .shadow(
                    elevation = textFieldElevation,
                    shape = TextFieldShape
                )
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = TextFieldShape
                )
        ) {

        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .then(if (isSearching) Modifier.width(IntrinsicSize.Min).padding(horizontal = 4.dp) else Modifier.width(values.minUnit(3)))
                .align(Alignment.Center)
                .padding(4.dp),
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = values.minUnit(6.5f).value.sp,
                textAlign = TextAlign.Start
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
            decorationBox = { innerTextField ->
                Box{
                    if (query.isEmpty()) {
                        BasicText(
                            text = "Search entities...",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
                                fontStyle = FontStyle.Italic
                            ),
                            maxLines = 1,
                            autoSize = TextAutoSize.StepBased(
                                minFontSize = 1.sp
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }

                    innerTextField()
                }
            }
        )
        }
    }
}

// Separate composable for the animated FAB
@Composable
private fun SearchToggleButton(
    isSearchOn: Boolean,
    onClick: () -> Unit,
    values: ScreenPhiUnits,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val fabScale by animateFloatAsState(
        targetValue = if (isPressed) ScreenPhiUnits.PHI_INV else 1.0f,
        animationSpec = PressSpring,
        label = "fabScale"
    )

    val animatedInnerCornerRadius by animateIntAsState(
        targetValue = if (isSearchOn) ScreenPhiUnits.PHICORNERPERCENT / 2 else ScreenPhiUnits.CIRCLEPERCENT,
        animationSpec = tween(300),
        label = "innerCornerRadius"
    )
    val animatedOuterCornerRadius by animateIntAsState(
        targetValue = if (isSearchOn) 100 else ScreenPhiUnits.CIRCLEPERCENT,
        animationSpec = tween(300),
        label = "outerCornerRadius"
    )

    FloatingActionButton(
        onClick = onClick,
        modifier = modifier
            .size(values.minUnit(5))
            .scale(fabScale),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(
            topStartPercent = animatedInnerCornerRadius,
            bottomStartPercent = animatedInnerCornerRadius,
            topEndPercent = animatedOuterCornerRadius,
            bottomEndPercent = animatedOuterCornerRadius
        ),
        interactionSource = interactionSource
    ) {
        AnimatedContent(
            targetState = isSearchOn,
            modifier = Modifier.size(values.minUnit(6)),
            transitionSpec = {
                // A more dynamic and visualwly appealing transition.
                fadeIn(tween(300)) + scaleIn(initialScale = 0.8f) togetherWith
                        fadeOut(tween(300)) + scaleOut(targetScale = 0.8f)
            },
            label = "iconContent"
        ) { isSearchOnState ->
            val rotation by animateFloatAsState(
                targetValue = if (isSearchOnState) 360f else 0f,
                animationSpec = BouncySpringFloat,
                label = "iconRotation"
            )

            val icon = if (isSearchOnState) Icons.Default.Close else Icons.Default.Search
            val color = if (isSearchOnState) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary

            Icon(
                imageVector = icon,
                contentDescription = stringResource(Res.string.search_toggle),
                tint = color,
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(rotation)
            )
        }
    }
}