package oqk.ananke.compedndium.entity.presentation.entity_lists

import oqk.ananke.compedndium.entity.domain.Entity

sealed interface EntityListAction {
    data class OnSearchQueryChange(val query: String): EntityListAction
    data class OnEntityClick(val entity: Entity): EntityListAction
    data class OnTabSelection(val index: Int): EntityListAction
}