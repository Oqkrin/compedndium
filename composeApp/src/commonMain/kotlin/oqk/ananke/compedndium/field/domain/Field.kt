package oqk.ananke.compedndium.field.domain

data class Field(
    val id: String,
    val title: String,
    val tags: List<String> = emptyList(),
)
