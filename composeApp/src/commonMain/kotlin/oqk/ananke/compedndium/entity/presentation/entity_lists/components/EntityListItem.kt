package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues
import oqk.ananke.compedndium.entity.domain.Entity

@Composable
fun EntityListItem(
    entity: Entity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    values: ScreenSizeDependentValues,
    maxTags: Int = 3
) {

    Surface(
        shape = RoundedCornerShape(values.cornerRadius),
        modifier = Modifier

    ) {
        Box(Modifier.background(MaterialTheme.colorScheme.primaryContainer)) {
            Box(Modifier.layout { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current dimension to assign width=height
                val currentWidth = placeable.height

                //assign the dimension and the position
                layout(width = currentWidth, height = currentWidth) {
                    // Where the composable gets placed
                    placeable.placeRelative(0, 0)
                } }.align(Alignment.Center)) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                    Box(
                        modifier = Modifier
                            .size(values.fabBaseSize)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(values.cornerRadius * 0.7f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            entity.type.icon,
                            contentDescription = entity.type.name,
                            modifier = Modifier.fillMaxSize(0.6f),
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Text(
                        entity.title,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2
                    )

                    if (entity.tags.isNotEmpty()) {
                        Text(
                            entity.tags.take(maxTags).joinToString(", ") { "#$it" },
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}



