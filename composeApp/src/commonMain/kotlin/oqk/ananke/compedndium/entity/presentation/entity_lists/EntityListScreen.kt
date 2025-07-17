package oqk.ananke.compedndium.entity.presentation.entity_lists

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import oqk.ananke.compedndium.entity.domain.Entity

@Composable
fun EntityListScreenRoot(
    viewModel: EntityListViewModel = koinViewModel(),
    OnEntityCLick: (Entity) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    EntityListScreen(
        state = state,
        OnAction = { action ->
            when(action) {
                is EntityListAction.OnEntityClick -> OnEntityCLick(action.entity)
                else -> Unit
            }
            viewModel.onAction(action)

        }
    )
}


@Composable
private fun EntityListScreen (
    state : EntityListState,
    OnAction: (EntityListAction) -> Unit

    ) {

}