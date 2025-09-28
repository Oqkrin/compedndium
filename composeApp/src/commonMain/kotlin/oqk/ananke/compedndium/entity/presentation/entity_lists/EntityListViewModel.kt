package oqk.ananke.compedndium.entity.presentation.entity_lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import oqk.ananke.compedndium.core.domain.WindowSizes
import oqk.ananke.compedndium.entity.data.EntityRepository
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType
import oqk.ananke.compedndium.entity.domain.filter.EntityFilter

/**
 * Manages UI state and business logic for the entity list screen.
 *
 * Handles:
 * - Entity filtering and search
 * - Theme management
 * - Debug mode for responsive design testing
 * - Window size tracking
 *
 * @property repository Data source for entities
 */
class EntityListViewModel(
    private val repository: EntityRepository
) : ViewModel() {
    private val _state = MutableStateFlow(EntityListState())
    val state = _state.asStateFlow()

    private var filterJob: Job? = null  // Active filtering coroutine

    init {
        loadEntities()
    }

    /**
     * Loads entities and applies initial filter
     */
    private fun loadEntities() = viewModelScope.launch {
        applyFilter()
    }

    /**
     * Applies current filter to entities in background thread
     * with cancellation support for rapid filter changes
     */
    private fun applyFilter() {
        filterJob?.cancel()  // Cancel previous filter operation
        filterJob = viewModelScope.launch(Dispatchers.Default) {
            val filtered = repository.getFilteredEntities(_state.value.filter)

            // Ensure filter hasn't changed during computation
            if (_state.value.filter == state.value.filter) {
                _state.update { it.copy(currentEntities = filtered) }
            }
        }
    }

    /**
     * Updates filter state and triggers re-filtering
     *
     * @param transform Transformation function for current filter
     */
    fun updateFilter(transform: (EntityFilter) -> EntityFilter) {
        _state.update { it.copy(filter = transform(it.filter)) }
        applyFilter()
    }

    /**
     * Handles all user interface actions
     *
     * @param action User-triggered event
     */
    fun onAction(action: EntityListAction) {
        when(action) {
            // Updates global search query
            is EntityListAction.OnSearchQueryChange -> {
                _state.update { it.copy(searchQuery = action.query, isSearching = action.query.isNotBlank())}
                updateFilter { it.withGlobalQuery(state.value.searchQuery) }
            }
            // Adds title-specific filter
            is EntityListAction.OnAddTitleFilter ->
                updateFilter { it.withTitleFilter(action.query) }

            // Adds tag-specific filters
            is EntityListAction.OnAddTagFilter ->
                updateFilter { it.withTagFilter(*action.tags.toTypedArray()) }

            // Clears all filters
            is EntityListAction.OnClearFilters -> updateFilter { it.clearFilters() }

            // Applies pre-defined filter set
            is EntityListAction.OnCustomFilterClick ->
                updateFilter { current ->
                    current.copy(
                        titleFilter = action.filter.titleFilter,
                        typeFilter = action.filter.typeFilter,
                        tagsFilter = action.filter.tagsFilter
                    )
                }

            // Toggles dark/light theme
            is EntityListAction.OnThemeToggle -> _state.update { it.copy(isDarkModeOn = !it.isDarkModeOn) }

            is EntityListAction.OnDynamicThemeToggle -> _state.update { it.copy(isDynamicThemeOn = !it.isDynamicThemeOn) }

            // Handles entity selection
            is EntityListAction.OnEntityClick ->
                handleEntityClick(action.entity)

            // Handles entity long-press
            is EntityListAction.OnEntityLongClick ->
                handleEntityLongClick(action.entity)

            // Handles creation actions
            is EntityListAction.OnCreateButtonClick ->
                handleCreateButtonClick(action.entity)

            // Handles long-press on create button
            is EntityListAction.OnCreateButtonLongClick ->
                handleCreateButtonLongClick(action.entityType)

            // Handles filter button
            is EntityListAction.OnFilterButtonClick ->
                handleFilterButtonClick(action.filter)

            // Handles view mode changes
            is EntityListAction.OnChangeModeButtonClick ->
                handleViewModeChange(action.mode)

            // Toggles debug window size mode
            is EntityListAction.OnDebugModeClick ->
                handleDebugModeToggle()

            // Updates current window size class
            is EntityListAction.OnWindowClassSizeChange ->
                _state.update { it.copy(currentWindowSize = action.windowSize) }

            EntityListAction.OnSearchToggle -> {
                _state.update { it.copy(isSearchOn = !it.isSearchOn) }

                if (!state.value.isSearchOn) {
                    viewModelScope.launch(Dispatchers.Default) {
                        delay(500)
                        onAction(EntityListAction.OnSearchQueryChange(""))
                        updateFilter { it.clearQuery() }
                    }
                }
            }

        }
    }

    private fun handleEntityClick(entity: Entity) {
        /* Handled in screen root */
    }

    private fun handleEntityLongClick(entity: Entity) {
        /* TODO: Implement selection mode */
    }

    private fun handleCreateButtonClick(entity: Entity?) {
        /* Handled in screen root */
    }

    private fun handleCreateButtonLongClick(entityType: EntityType) {
        /* TODO: Show entity type selector */
    }

    private fun handleFilterButtonClick(filter: String) {
        /* TODO: Show filter dialog */
    }

    private fun handleViewModeChange(mode: Boolean) {
        /* TODO: Implement view mode changes */
    }

    /**
     * Manages debug window size cycling for responsive design testing
     */
    private fun handleDebugModeToggle() {

        if (state.value.isDebugSizeOn) {
            // Cycle through window sizes
            val nextSize = when (state.value.debugWindowSize) {
                null -> WindowSizes.PHONE_PORTRAIT
                WindowSizes.PHONE_PORTRAIT -> WindowSizes.PHONE_LANDSCAPE
                WindowSizes.PHONE_LANDSCAPE -> WindowSizes.TABLET_PORTRAIT
                WindowSizes.TABLET_PORTRAIT -> WindowSizes.TABLET_LANDSCAPE
                WindowSizes.TABLET_LANDSCAPE -> WindowSizes.DESKTOP
                WindowSizes.DESKTOP -> null
                WindowSizes.INITIALIZING -> WindowSizes.PHONE_PORTRAIT
                else -> WindowSizes.PHONE_PORTRAIT
            }

            _state.update {
                it.copy(debugWindowSize = nextSize)
            }

            // Turn off debug mode after cycling through all sizes
            if (nextSize == null) {
                _state.update { it.copy(isDebugSizeOn = false) }
            }
        } else {
            // Enable debug mode
            _state.update { it.copy(isDebugSizeOn = true) }
        }
    }
}