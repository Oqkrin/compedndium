package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListAction

@Composable
fun FloatingCreateButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    corner: Dp,
    opacity: Float,
    iconSize: Dp
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.semantics {
            contentDescription = "Create new item"
            role = Role.Button
        },
        shape = RoundedCornerShape(corner),
        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = opacity),
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(iconSize)
            )
        }
    )
}

