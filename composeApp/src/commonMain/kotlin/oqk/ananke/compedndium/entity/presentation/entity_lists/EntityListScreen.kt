package oqk.ananke.compedndium.entity.presentation.entity_lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneDisabled
import androidx.compose.material.icons.filled.PhoneEnabled
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType
import oqk.ananke.compedndium.entity.presentation.entity_lists.components.EntityListItem
import oqk.ananke.compedndium.entity.presentation.entity_lists.components.FloatingCreateButton
import oqk.ananke.compedndium.entity.presentation.entity_lists.components.FloatingSearch
import oqk.ananke.compedndium.entity.presentation.entity_lists.components.FloatingTabSelector

@Composable
fun EntityListScreenRoot(
    viewModel: EntityListViewModel = koinViewModel(),
    onEntityClick: (Entity) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    EntityListScreen(
        state = state,
        onAction = { action -> 
            when(action) {
                is EntityListAction.OnChangeModeButtonClick -> TODO()
                is EntityListAction.OnCreateButtonClick -> TODO()
                is EntityListAction.OnCreateButtonLongClick -> TODO()
                is EntityListAction.OnEntityClick -> onEntityClick(action.entity)
                is EntityListAction.OnEntityLongClick -> TODO()
                is EntityListAction.OnFilterButtonClick -> TODO()
                is EntityListAction.OnSearchQueryChange -> TODO()
                is EntityListAction.OnTabSelection -> TODO()
            }
        },
        modifier = modifier
    )
}


@Composable
private fun EntityListScreen(
    state: EntityListState,
    onAction: (EntityListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    MaterialTheme(colorScheme = darkColorScheme()) {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
            ) {
                var phoneMode by rememberSaveable { mutableStateOf(false) }
                val values = rememberScreenValues(maxWidth, maxHeight, phoneMode)
                
                ScreenContainer(
                    values = values,
                    phoneMode = phoneMode,
                    onPhoneModeToggle = { phoneMode = !phoneMode },
                    onAction = onAction
                )
            }
        }
    }
}

@Composable
private fun rememberScreenValues(
    maxWidth: androidx.compose.ui.unit.Dp,
    maxHeight: androidx.compose.ui.unit.Dp,
    phoneMode: Boolean
) = remember(maxWidth, maxHeight, phoneMode) {
    if (phoneMode) {
        ScreenSizeDependentValues(
            screenWidth = (225.dp) * 3 / 2,
            screenHeight = (500.dp) * 3 / 2
        )
    } else {
        ScreenSizeDependentValues(maxWidth, maxHeight)
    }
}

@Composable
private fun ScreenContainer(
    values: ScreenSizeDependentValues,
    phoneMode: Boolean,
    onPhoneModeToggle: () -> Unit,
    onAction: (EntityListAction) -> Unit
) {
    Box(
        Modifier
            .width(values.screenWidth)
            .height(values.screenHeight)
            .background(MaterialTheme.colorScheme.error)
    ) {
        PhoneModeToggle(
            phoneMode = phoneMode,
            onToggle = onPhoneModeToggle,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        
        SampleEntityItem(values = values)
        
        FloatingActionBar(
            values = values,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun PhoneModeToggle(
    phoneMode: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    SmallFloatingActionButton(
        onClick = onToggle,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (phoneMode) Icons.Default.PhoneEnabled else Icons.Default.PhoneDisabled,
            contentDescription = "View Mode"
        )
    }
}

@Composable
private fun SampleEntityItem(values: ScreenSizeDependentValues) {
    EntityListItem(
        entity = Entity(
            id = "0",
            type = EntityType.PC,
            tags = listOf("Dnd", "OC"),
            title = "Olivander"
        ),
        onClick = { },
        values = values
    )
}

@Composable
private fun FloatingActionBar(
    values: ScreenSizeDependentValues,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(bottom = values.bottomPadding)
            .width(values.fabBarSize)
            .background(MaterialTheme.colorScheme.background)
    ) {
        SearchRow(values = values)
        TabsAndCreateRow(values = values)
    }
}

@Composable
private fun SearchRow(values: ScreenSizeDependentValues) {
    Row(
        modifier = Modifier
            .width(values.fabBarSize)
            .background(MaterialTheme.colorScheme.background),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.width(values.fabZonesSize))
        
        Box(Modifier.width(values.fabTabZoneSize + values.fabZonesSize)) {
            FloatingSearch(
                searchIcons = arrayOf(Icons.Default.SearchOff, Icons.Default.Search),
                searchButtonModifier = Modifier.size(values.fabLargeTabSide),
                searchMenuModifier = Modifier.align(Alignment.BottomEnd),
                opacity = values.fabOpacity,
                corner = values.cornerRadius,
                iconSize = values.iconSize,
                values = values
            )
        }
    }
}

@Composable
private fun TabsAndCreateRow(values: ScreenSizeDependentValues) {
    Row(
        modifier = Modifier
            .width(values.fabBarSize)
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(values.fabZonesSize))
        
        FloatingTabSelector(
            entityTypes = EntityType.entries,
            tabsButtonModifier = Modifier.size(
                width = values.fabLargeTabSide,
                height = values.fabSmallTabSide
            ),
            tabMenuModifier = Modifier
                .width(values.fabTabZoneSize)
                .background(MaterialTheme.colorScheme.background),
            corner = values.cornerRadius,
            opacity = values.fabOpacity,
            iconSize = values.smallIconSize
        )
        
        Box(modifier = Modifier.width(values.fabZonesSize)) {
            FloatingCreateButton(
                createModifier = Modifier
                    .size(values.fabLargeTabSide)
                    .align(Alignment.BottomEnd),
                corner = values.cornerRadius,
                opacity = values.fabOpacity,
                iconSize = values.iconSize
            )
        }
    }
}