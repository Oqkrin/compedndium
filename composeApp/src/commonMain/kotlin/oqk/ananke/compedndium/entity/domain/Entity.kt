package oqk.ananke.compedndium.entity.domain

import oqk.ananke.compedndium.field.domain.Field

/**
 * Represents an entity with core attributes
 */
data class Entity(
    val id: String,
    val title: String,
    val tags: List<String> = emptyList(),
    val type: EntityType,
    val fields: List<Field> = emptyList(),
    var isFavorite: Boolean = false
)