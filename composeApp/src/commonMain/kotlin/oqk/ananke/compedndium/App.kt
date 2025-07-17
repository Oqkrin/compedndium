package oqk.ananke.compedndium

import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import oqk.ananke.compedndium.core.presentation.components.FloatingSearch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    var expanded by rememberSaveable { mutableStateOf(false) }

    MaterialTheme {

        FloatingSearch(
            onclick = { },
            icon = TODO(),
            inputfield = TODO(),
            expanded = TODO(),
            onExpandedChange = TODO(),
            suggestions = TODO()
        )
    }
}

