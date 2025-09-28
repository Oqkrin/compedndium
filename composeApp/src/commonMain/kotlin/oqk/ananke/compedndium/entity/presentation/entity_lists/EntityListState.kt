package oqk.ananke.compedndium.entity.presentation.entity_lists

import oqk.ananke.compedndium.core.domain.WindowSizes
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.filter.EntityFilter

/**
 * Represents the complete UI state for the entity list screen
 *
 * @property currentEntities Currently visible entities after filtering
 * @property favorites User's favorite entities
 * @property filter Current active filter configuration
 * @property isDarkModeOn Whether dark theme is enabled
 * @property isDebugSizeOn Whether debug window sizing is active
 * @property debugWindowSize Currently simulated window size in debug mode
 * @property currentWindowSize Actual current window size class
 */
data class EntityListState(
    val currentEntities: List<Entity> = emptyList(),
    val favorites: List<Entity> = emptyList(),
    val filter: EntityFilter = EntityFilter(),
    val isDarkModeOn: Boolean = false,
    val isDynamicThemeOn: Boolean = true,
    val isDebugSizeOn: Boolean = false,
    val isSearchOn: Boolean = false,
    val isSearching: Boolean = false,
    val searchQuery: String = "",
    val debugWindowSize: WindowSizes? = null,
    val currentWindowSize: WindowSizes = WindowSizes.INITIALIZING
)
