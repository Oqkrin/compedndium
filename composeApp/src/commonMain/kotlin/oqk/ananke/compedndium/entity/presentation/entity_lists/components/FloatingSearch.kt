package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues

@Composable
fun FloatingSearch(
    searchIcon: ImageVector,
    closeIcon: ImageVector,
    onSearchQuery: (String) -> Unit,
    modifier: Modifier = Modifier,
    values: ScreenSizeDependentValues
) {
    val searchState = remember { SearchState() }
    val textState = rememberTextFieldState()

    LaunchedEffect(textState.text, searchState.isExpanded) {
        val query = if (searchState.isExpanded) textState.text.toString() else ""
        searchState.updateQuery(query)
        onSearchQuery(query)
    }

    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        if (searchState.isExpanded) {
            BasicTextField(
                state = textState,
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimaryContainer),
                lineLimits = TextFieldLineLimits.SingleLine,
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = values.fabOpacity),
                        RoundedCornerShape(values.cornerRadius)
                    )
                    .width(values.fabTabZoneSize)
                    .padding(values.minSize / 30)
            )
        }
        
        FloatingActionButton(
            onClick = searchState::toggle,
            shape = RoundedCornerShape(values.cornerRadius),
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = values.fabOpacity)
        ) {
            Icon(
                imageVector = if (searchState.isExpanded) closeIcon else searchIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(values.iconSize)
            )
        }
    }
}