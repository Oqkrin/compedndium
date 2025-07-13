package oqk.ananke.compedndium.entity.core

import oqk.ananke.compedndium.field.core.Field

data class Entity(
    val id: String,
    val type: EntityType,
    val tag: String,
    val title: String,
    val fields: List<Field>
)