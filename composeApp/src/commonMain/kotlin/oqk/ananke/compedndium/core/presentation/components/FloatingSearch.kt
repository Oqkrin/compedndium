package oqk.ananke.compedndium.core.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingSearch(
    onclick: () -> Unit,
    icon: @Composable (() -> Unit),
    inputfield: @Composable (() -> Unit),
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    suggestions: @Composable (ColumnScope.() -> Unit)
) {
    FloatingActionButton(
        onClick = onclick,
        content = icon
    )
    SearchBar(
        inputField = inputfield,
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        content = suggestions
    )
}

