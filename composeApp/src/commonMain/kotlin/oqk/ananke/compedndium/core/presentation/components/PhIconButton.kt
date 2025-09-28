package oqk.ananke.compedndium.core.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import oqk.ananke.compedndium.core.data.ScreenPhiUnits
import org.jetbrains.compose.resources.StringResource

@Composable
fun PhIconButton(
    title: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconContainerModifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: Color = LocalContentColor.current,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    shape: Shape = RoundedCornerShape(ScreenPhiUnits.PHICORNERPERCENT),
    content: @Composable (BoxScope.() -> Unit) = {}
) {
    //for style
    FloatingActionButton(
        shape = shape,
        onClick = onClick,
        modifier = modifier.semantics(mergeDescendants = true) {
            contentDescription = title.toString()
        },
        containerColor = containerColor
    ) {
        //box that will get the desired size
        Box(
            modifier = iconContainerModifier,
        ) {
            Icon(
                imageVector = icon,
                modifier = iconModifier.fillMaxSize(), //fill max size so the icon fills the available desired
                contentDescription = null,             //while maintaining a crispy look
                tint = iconColor
            )
            content()
        }
    }
}
