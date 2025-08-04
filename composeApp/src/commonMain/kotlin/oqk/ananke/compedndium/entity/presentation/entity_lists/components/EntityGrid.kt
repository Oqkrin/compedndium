package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues
import oqk.ananke.compedndium.entity.domain.Entity

@Composable
fun EntityGrid(
    entities: List<Entity>,
    onEntityClick: (Entity) -> Unit,
    values: ScreenSizeDependentValues,
    modifier: Modifier = Modifier
) {
    val entityItemSize = values.minSize / 3
    val gridSpacing = values.calcPadding(values.minSize)
    val gridPadding = values.bottomPadding / 2


    val sortedEntities = remember(entities) {
        derivedStateOf { entities.sortedBy { it.title } }
    }
    
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = entityItemSize),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(gridPadding),
        verticalArrangement = Arrangement.spacedBy(gridSpacing),
        horizontalArrangement = Arrangement.spacedBy(gridSpacing)
    ) {
        items(sortedEntities.value) { entity ->
            EntityListItem(
                entity = entity,
                onClick = { onEntityClick(entity) },
                values = values
            )
        }
    }
}