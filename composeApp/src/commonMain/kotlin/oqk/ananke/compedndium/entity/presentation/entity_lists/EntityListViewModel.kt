package oqk.ananke.compedndium.entity.presentation.entity_lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import oqk.ananke.compedndium.core.domain.onError
import oqk.ananke.compedndium.core.domain.onSuccess
import oqk.ananke.compedndium.core.presentation.UiText
import oqk.ananke.compedndium.entity.domain.EntityRepository

class EntityListViewModel(
    private val repository: EntityRepository
): ViewModel() {
    private val _state = MutableStateFlow(EntityListState())
    val state = _state.asStateFlow()
    
    init {
        loadEntities()
    }

    private fun loadEntities() {
        viewModelScope.launch {
            repository.getEntities()
                .onSuccess { entities ->
                    _state.update { it.copy(currentEntities = entities) }
                }
                .onError { error ->
                    _state.update { it.copy(errorMessage = UiText.DynamicString(error.toString())) }
                }
        }
    }
    
    private fun searchEntities(query: String) {
        viewModelScope.launch {
            repository.searchEntities(query)
                .onSuccess { entities ->
                    val filteredEntities = filterBySelectedTab(entities)
                    _state.update { it.copy(currentEntities = filteredEntities, searchQuery = query) }
                }
                .onError { error ->
                    _state.update { it.copy(errorMessage = UiText.DynamicString(error.toString())) }
                }
        }
    }
    
    private fun filterBySelectedTab(entities: List<Entity>): List<Entity> {
        val selectedType = EntityType.entries.getOrNull(_state.value.tabIndex)
        return if (selectedType != null) {
            entities.filter { it.type == selectedType }
        } else entities
    }
    
    private fun loadEntitiesForTab(tabIndex: Int) {
        viewModelScope.launch {
            val query = _state.value.searchQuery
            val result = if (query.isBlank()) repository.getEntities() else repository.searchEntities(query)
            
            result.onSuccess { entities ->
                val selectedType = EntityType.entries.getOrNull(tabIndex)
                val filteredEntities = if (selectedType != null) {
                    entities.filter { it.type == selectedType }
                } else entities
                
                _state.update { it.copy(currentEntities = filteredEntities, tabIndex = tabIndex) }
            }
        }
    }

    fun onAction(action: EntityListAction) {
        when(action) {
            is EntityListAction.OnSearchQueryChange -> {
                searchEntities(action.query)
            }
            is EntityListAction.OnTabSelection -> {
                loadEntitiesForTab(action.entityType.ordinal)
            }
            is EntityListAction.OnEntityClick -> {}
            is EntityListAction.OnChangeModeButtonClick -> {}
            is EntityListAction.OnCreateButtonClick -> {}
            is EntityListAction.OnCreateButtonLongClick -> {}
            is EntityListAction.OnEntityLongClick -> {}
            is EntityListAction.OnFilterButtonClick -> {}
        }
    }
}