package oqk.ananke.compedndium.entity.domain

import oqk.ananke.compedndium.field.domain.Field

data class Entity(
    val id: String,
    val type: EntityType,
    val tags: List<String>,
    val title: String,
    val fields: List<Field> = emptyList()
)