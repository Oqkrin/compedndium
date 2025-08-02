package oqk.ananke.compedndium.entity.presentation.entity_lists

import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType

sealed interface EntityListAction {
    data class OnSearchQueryChange(val query: String): EntityListAction
    data class OnEntityClick(val entity: Entity): EntityListAction
    data class OnEntityLongClick(val entity: Entity): EntityListAction
    data class OnFilterButtonClick(val filter: String) : EntityListAction
    data class OnChangeModeButtonClick(val mode: Boolean): EntityListAction
    data class OnCreateButtonClick(val entity: Entity?): EntityListAction
    data class OnCreateButtonLongClick(val entityType: EntityType): EntityListAction
    data class OnTabSelection(val entityType: EntityType): EntityListAction
}