package oqk.ananke.compedndium.entity.presentation.entity_lists

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EntityListViewModel: ViewModel() {
    private val _state = MutableStateFlow(EntityListState())
    val state = _state.asStateFlow()

    fun onAction(action: EntityListAction) {
        when(action) {
            is EntityListAction.OnEntityClick -> {

            }
            is EntityListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is EntityListAction.OnTabSelection -> {
                _state.update {
                    it.copy(tabIndex = action.index)
                }
            }
        }
    }
}