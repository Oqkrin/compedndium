package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import oqk.ananke.compedndium.entity.domain.EntityType
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListAction

@Composable
fun FloatingTabSelector(
    entityTypes: List<EntityType>,
    selectedTabIndex: Int,
    onTabSelect: (EntityType) -> Unit,
    modifier: Modifier = Modifier,
    tabsButtonModifier: Modifier = Modifier,
    corner: Dp,
    iconSize: Dp,
    opacity: Float = 1.0f
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceAround) {
        entityTypes.forEachIndexed { index, entityType ->
            val isSelected = index == selectedTabIndex
            FloatingActionButton(
                onClick = { onTabSelect(entityType) },
                content = { 
                    Icon(
                        imageVector = entityType.icon, 
                        contentDescription = entityType.name,
                        Modifier.size(iconSize)
                    ) 
                },
                modifier = tabsButtonModifier,
                shape = RoundedCornerShape(corner),
                containerColor = if (isSelected) 
                    MaterialTheme.colorScheme.primary.copy(alpha = opacity)
                else 
                    MaterialTheme.colorScheme.primaryContainer.copy(alpha = opacity),
            )
        }
    }
}
