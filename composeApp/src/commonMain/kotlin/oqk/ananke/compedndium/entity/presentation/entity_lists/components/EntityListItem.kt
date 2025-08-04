package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues
import oqk.ananke.compedndium.entity.domain.Entity

@Composable
fun EntityListItem(
    entity: Entity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    values: ScreenSizeDependentValues,
    maxTags: Int = 2
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(values.cornerRadius),
        modifier = modifier
            .aspectRatio(1f)
            .semantics {
                contentDescription = "${entity.type.name}: ${entity.title}"
                role = Role.Button
            },
        color = MaterialTheme.colorScheme.surfaceContainer
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(values.minSize / 60), // Minimal padding for max space
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            // Icon takes top 40% of space
            Icon(
                imageVector = entity.type.icon,
                contentDescription = entity.type.name,
                modifier = Modifier
                    .size(values.minSize / 12)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.primary
            )
            
            // Title takes middle space
            Text(
                text = entity.title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = MaterialTheme.typography.titleMedium.fontSize * 1.3f
                ),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )

            // Tags take bottom space
            if (entity.tags.isNotEmpty()) {
                Text(
                    text = entity.tags.take(maxTags).joinToString(" ") { "#$it" },
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize * 1.2f
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}