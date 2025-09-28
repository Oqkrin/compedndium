package oqk.ananke.compedndium.entity.presentation.entity_lists

import oqk.ananke.compedndium.core.domain.WindowSizes
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType
import oqk.ananke.compedndium.entity.domain.filter.EntityFilter

/**
 * Sealed interface representing all possible user actions
 * in the entity list screen
 */
sealed interface EntityListAction {
    /** Window size class changed (responsive design) */
    data class OnWindowClassSizeChange(val windowSize: WindowSizes) : EntityListAction

    /** Search query changed */
    data class OnSearchQueryChange(val query: String) : EntityListAction

    data object OnSearchToggle : EntityListAction

    /** Entity clicked */
    data class OnEntityClick(val entity: Entity) : EntityListAction

    /** Entity long-pressed */
    data class OnEntityLongClick(val entity: Entity) : EntityListAction

    /** Create button clicked */
    data class OnCreateButtonClick(val entity: Entity?) : EntityListAction

    /** Create button long-pressed */
    data class OnCreateButtonLongClick(val entityType: EntityType) : EntityListAction

    /** View mode changed (grid/list/card) */
    data class OnChangeModeButtonClick(val mode: Boolean) : EntityListAction

    /** Filter button clicked */
    data class OnFilterButtonClick(val filter: String) : EntityListAction

    /** Pre-defined filter applied */
    data class OnCustomFilterClick(val filter: EntityFilter) : EntityListAction

    /** Theme toggle clicked */
    data object OnThemeToggle : EntityListAction

    data object OnDynamicThemeToggle : EntityListAction

    /** Debug mode toggle clicked */
    data object OnDebugModeClick : EntityListAction

    // Filter management actions
    /** Title filter added */
    data class OnAddTitleFilter(val query: String) : EntityListAction

    /** Tag filters added */
    data class OnAddTagFilter(val tags: List<String>) : EntityListAction

    /** All filters cleared */
    data object OnClearFilters : EntityListAction

}