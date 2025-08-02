package oqk.ananke.compedndium.entity.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class EntityType(val icon: ImageVector) {
    PC(icon = Icons.Default.Groups3),
    NPC(icon = Icons.Default.FlutterDash),
    ABILITIES(icon = Icons.Default.Flare),
    WORLD(icon= Icons.Default.Language),
    OBJECT(icon = Icons.Default.Category)
}
