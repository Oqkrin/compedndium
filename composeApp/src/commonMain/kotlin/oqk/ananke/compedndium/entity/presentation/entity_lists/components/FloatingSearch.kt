package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListAction
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListViewModel
@Composable
fun FloatingSearch(
    searchIcons: Array<ImageVector>,
    searchMenuModifier: Modifier = Modifier,
    searchButtonModifier: Modifier = Modifier,
    corner: Dp,
    opacity: Float,
    iconSize: Dp,
    values: ScreenSizeDependentValues
) {
    var searchState by rememberSaveable { mutableStateOf(false) }
    var isReadonly  by rememberSaveable { mutableStateOf(false) }

    Row(modifier = searchMenuModifier, verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        val searchTextState = rememberTextFieldState()

        if (searchState) {

            remember(searchTextState.text) {
                if (searchTextState.text.length >= "awdawdawdawdawdawdawdaw".length) {
                    isReadonly = !isReadonly
                } else {
                    EntityListAction.OnSearchQueryChange(searchTextState.text as String)
                }
            }
                BasicTextField(
                    state = searchTextState,
                    textStyle = TextStyle.Default.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    lineLimits = TextFieldLineLimits.SingleLine,
                    modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
                    readOnly = isReadonly
                )

            Spacer(modifier = Modifier.width(values.calcPadding(values.fabZonesSize+values.fabTabZoneSize)))

        }

        FloatingActionButton(
            onClick = { searchState = !searchState },
            modifier = Modifier.semantics { 
                contentDescription = "Toggle search"
                role = Role.Switch 
            }.then(searchButtonModifier),
            shape = RoundedCornerShape(corner),
            containerColor = (if (searchState) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.primaryContainer).copy(alpha = opacity),
            content = {
                val currentIcon = if (searchState) searchIcons[0] else searchIcons[1]
                val description = if (searchState) "Close search" else "Open search"
                
                Icon(
                    imageVector = currentIcon, 
                    contentDescription = description,
                    tint = if (searchState) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.size(iconSize)
                )
            }
        )

    }
}
