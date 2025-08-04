package oqk.ananke.compedndium.entity.presentation.entity_lists

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneDisabled
import androidx.compose.material.icons.filled.PhoneEnabled
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType

import oqk.ananke.compedndium.core.presentation.components.ErrorDisplay
import oqk.ananke.compedndium.entity.presentation.entity_lists.components.EntityGrid
import oqk.ananke.compedndium.entity.presentation.entity_lists.components.FloatingActionBar

@Composable
fun EntityListScreenRoot(
    onEntityClick: (Entity) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntityListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    EntityListScreen(
        state = state,
        onAction = { action -> 
            when(action) {
                is EntityListAction.OnEntityClick -> onEntityClick(action.entity)
                else -> viewModel.onAction(action)
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
                
                val displayEntities = state.currentEntities
                
                if (state.errorMessage != null) {
                    ErrorDisplay(
                        error = state.errorMessage,
                        onRetry = { /* TODO: Add retry logic */ },
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    ScreenContainer(
                        entities = displayEntities,
                        values = values,
                        phoneMode = phoneMode,
                        state = state,
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
    entities: List<Entity>,
    values: ScreenSizeDependentValues,
    phoneMode: Boolean,
    state: EntityListState,
    onPhoneModeToggle: () -> Unit,
    onAction: (EntityListAction) -> Unit
) {
    Box(
        modifier = Modifier
            .size(values.screenWidth, values.screenHeight)
    ) {
        EntityGrid(
            entities = entities,
            onEntityClick = { entity -> onAction(EntityListAction.OnEntityClick(entity)) },
            values = values
        )
        
        PhoneModeToggle(
            phoneMode = phoneMode,
            onToggle = onPhoneModeToggle,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .zIndex(1f)
        )
        
        FloatingActionBar(
            values = values,
            selectedTabIndex = state.tabIndex,
            onAction = onAction,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .zIndex(1f)
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



