package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues
import oqk.ananke.compedndium.entity.domain.EntityType
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListAction

@Composable
fun FloatingActionBar(
    values: ScreenSizeDependentValues,
    selectedTabIndex: Int,
    onAction: (EntityListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(bottom = values.bottomPadding)
            .width(values.fabBarSize)
    ) {
        SearchRow(values = values, onAction = onAction)
        TabsAndCreateRow(values = values, selectedTabIndex = selectedTabIndex, onAction = onAction)
    }
}

@Composable
private fun SearchRow(
    values: ScreenSizeDependentValues,
    onAction: (EntityListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.width(values.fabBarSize),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(Modifier.width(values.fabZonesSize))
        
        Box(Modifier.width(values.fabTabZoneSize + values.fabZonesSize)) {
            FloatingSearch(
                searchIcon = Icons.Default.Search,
                closeIcon = Icons.Default.SearchOff,
                onSearchQuery = { query -> onAction(EntityListAction.OnSearchQueryChange(query)) },
                modifier = Modifier.align(Alignment.BottomEnd),
                values = values
            )
        }
    }
}

@Composable
private fun TabsAndCreateRow(
    values: ScreenSizeDependentValues,
    selectedTabIndex: Int,
    onAction: (EntityListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.width(values.fabBarSize),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(values.fabZonesSize))
        
        FloatingTabSelector(
            entityTypes = EntityType.entries,
            selectedTabIndex = selectedTabIndex,
            onTabSelect = { entityType -> onAction(EntityListAction.OnTabSelection(entityType)) },
            modifier = Modifier.width(values.fabTabZoneSize),
            tabsButtonModifier = Modifier.size(
                width = values.fabLargeTabSide,
                height = values.fabSmallTabSide
            ),
            corner = values.cornerRadius,
            opacity = values.fabOpacity,
            iconSize = values.smallIconSize
        )
        
        Box(modifier = Modifier.width(values.fabZonesSize)) {
            FloatingCreateButton(
                onClick = { onAction(EntityListAction.OnCreateButtonClick(null)) },
                modifier = Modifier
                    .size(values.fabLargeTabSide)
                    .align(Alignment.BottomEnd),
                corner = values.cornerRadius,
                opacity = values.fabOpacity,
                iconSize = values.iconSize
            )
        }
    }
}