package oqk.ananke.compedndium.entity.presentation.entity_lists

import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType
import oqk.ananke.compedndium.core.presentation.UiText

data class EntityListState(
    val searchQuery: String = "Search...",
    val searchResults: List<Entity> = emptyList(),
    val favorites: List<Entity> = emptyList(),
    val types: List<EntityType> = emptyList(),
    val tabIndex: Int = 0,
    val errorMessage: UiText? = null
)
