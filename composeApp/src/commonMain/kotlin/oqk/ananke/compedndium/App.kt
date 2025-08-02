package oqk.ananke.compedndium

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListScreenRoot
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    EntityListScreenRoot(
        viewModel = remember { EntityListViewModel() },
        onEntityClick = {}
    )
}
