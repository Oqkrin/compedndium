package oqk.ananke.compedndium.entity.container.entity_lists

import oqk.ananke.compedndium.entity.core.Entity
import oqk.ananke.compedndium.entity.core.EntityType

data class EntityListsState(
    val searchQuery: String = "Search...",
    val searchResults: List<Entity> = emptyList(),
    val favorites: List<Entity> = emptyList(),
    val types: List<EntityType> = emptyList(),

)
