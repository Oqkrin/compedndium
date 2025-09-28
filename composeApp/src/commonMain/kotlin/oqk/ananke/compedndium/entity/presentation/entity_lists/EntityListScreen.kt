package oqk.ananke.compedndium.entity.presentation.entity_lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DashboardCustomize
import androidx.compose.material.icons.filled.DeviceHub
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LogoDev
import androidx.compose.material.icons.filled.Monitor
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.DashboardCustomize
import androidx.compose.material.icons.outlined.DeviceHub
import androidx.compose.material.icons.outlined.Devices
import androidx.compose.material.icons.outlined.LogoDev
import androidx.compose.material.icons.outlined.Monitor
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import compedndium.composeapp.generated.resources.Res
import compedndium.composeapp.generated.resources.create_new_item
import compedndium.composeapp.generated.resources.theme_mode
import oqk.ananke.compedndium.core.data.ScreenPhiUnits
import oqk.ananke.compedndium.core.domain.WindowSizes
import oqk.ananke.compedndium.core.presentation.components.PhIconButton
import oqk.ananke.compedndium.core.presentation.components.SearchBar
import oqk.ananke.compedndium.core.presentation.theme.CompedndiumTheme
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.presentation.entity_lists.components.Favorites
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun EntityListScreenRoot(
    onEntityClick: (Entity) -> Unit,
    onCreateEntity: (Entity?) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: EntityListViewModel = koinViewModel(),
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CompedndiumTheme(isDynamicThemeOn = state.isDynamicThemeOn, isDarkThemeOn = state.isDarkModeOn) {
        EntityListScreen(
            state = state,
            windowSizeClass = windowSizeClass,
            onAction = { action -> 
                when(action) {
                    is EntityListAction.OnEntityClick -> onEntityClick(action.entity)
                    is EntityListAction.OnCreateButtonClick -> onCreateEntity(action.entity)
                    else -> viewModel.onAction(action)
                }
            },
            modifier = modifier
        )
    }
}

@Composable
private fun EntityListScreen(
    state: EntityListState,
    windowSizeClass: WindowSizeClass,
    onAction: (EntityListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier) {
            val (wDebug, hDebug) = remember(state.debugWindowSize) {
                if (state.isDebugSizeOn)
                    state.debugWindowSize?.minWidth to state.debugWindowSize?.minHeight
                else null to null
            }

            remember(windowSizeClass) {
                onAction(
                    EntityListAction.OnWindowClassSizeChange(
                        WindowSizes.match(
                            windowSizeClass.windowWidthSizeClass,
                            windowSizeClass.windowHeightSizeClass
                        )
                    )
                )
            }

            val values = remember(wDebug, hDebug, maxHeight, maxWidth) {
                ScreenPhiUnits(
                    wDebug ?: maxWidth,
                    hDebug ?: maxHeight,
                    windowSizeClass.windowWidthSizeClass,
                    windowSizeClass.windowHeightSizeClass
                )
            }

            Box(
                Modifier
                    .size(values.wUnit(0), values.hUnit(0))
                    .windowInsetsPadding(WindowInsets.systemBars)
                    .align(Alignment.Center)
                    .background(MaterialTheme.colorScheme.background)
            ) {

                Favorites(
                    Modifier.padding(top = values.screenHeightPadding).align(Alignment.TopCenter),
                    values
                )


                DebugToggles(
                    windowSize = state.debugWindowSize ?: state.currentWindowSize,
                    isDebugSizeOn = state.isDebugSizeOn,
                    isDarkMode = state.isDarkModeOn,
                    isDynamicModeOn = state.isDynamicThemeOn,
                    onAction = onAction,
                    modifier = Modifier.zIndex(1f)
                )

                SearchBar(
                    modifier = Modifier.zIndex(1f).align(if (values.isLandscape) Alignment.TopEnd else Alignment.BottomEnd)
                        .padding(bottom = values.screenHeightPadding + values.minUnit(4) + 4.dp, top = values.screenHeightPadding)
                        .padding(horizontal = values.screenWidthPadding),
                    state = state,
                    onAction = onAction,
                    values = values,
                )


                PhIconButton(
                    title = stringResource(Res.string.create_new_item),
                    onClick = { onAction(EntityListAction.OnCreateButtonClick(null)) },
                    modifier = Modifier
                        .zIndex(1f)
                        .padding(values.screenWidthPadding, values.screenHeightPadding)
                        .size(values.minUnit(4))
                        .align(Alignment.BottomEnd)
                        ,
                    iconContainerModifier = Modifier.size(values.minUnit(5)),
                    icon = Icons.Default.Add,
                    iconColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            }
        }
    }
}
@Composable
private fun DebugToggles(
    windowSize: WindowSizes,
    isDebugSizeOn: Boolean,
    isDarkMode: Boolean,
    isDynamicModeOn: Boolean,
    onAction: (EntityListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val lightDarkModeIcon = remember(isDarkMode) {
        if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode
    }

    val dynamicThemeIcon = remember(isDynamicModeOn) {
        if (isDynamicModeOn) Icons.Filled.DashboardCustomize else Icons.Outlined.DashboardCustomize
    }

    val debugSizeIcon = remember(isDebugSizeOn) {
        if (isDebugSizeOn) Icons.Filled.Devices else Icons.Outlined.Devices
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(intrinsicSize = IntrinsicSize.Min)
            .height(intrinsicSize = IntrinsicSize.Min)
            .padding(8.dp)
            .shadow(6.dp, CircleShape)
            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
    ) {
        Row {
            IconButton(
                onClick = { onAction(EntityListAction.OnThemeToggle) }
            ) {
                Icon(
                    imageVector = lightDarkModeIcon,
                    contentDescription = stringResource(Res.string.theme_mode)
                )
            }

            IconButton(
                onClick = { onAction(EntityListAction.OnDynamicThemeToggle) }
            ) {
                Icon(
                    imageVector = dynamicThemeIcon,
                    contentDescription = stringResource(Res.string.theme_mode)
                )
            }

            IconButton(
                onClick = { onAction(EntityListAction.OnDebugModeClick) }
            ) {
                Icon(
                    imageVector = debugSizeIcon,
                    contentDescription = windowSize.displayName
                )
            }
        }

        BasicText(
            modifier = Modifier.fillMaxWidth(),
            text = windowSize.displayName,
            style = MaterialTheme.typography.labelSmall.copy(textAlign = TextAlign.Center),
            autoSize = TextAutoSize.StepBased(minFontSize = 1.sp),
            maxLines = 1
        )

    }
}

