package oqk.ananke.compedndium.entity.domain.filter

import oqk.ananke.compedndium.entity.domain.Entity

/**
 * Reusable predicate logic for entity filtering
 */
object EntityFilterPredicates {
    /**
     * Checks if entity matches global search query
     */
    fun matchesGlobal(entity: Entity, query: String): Boolean {
        val normalized = query.lowercase()
        return entity.title.contains(normalized, true) ||
                entity.type.name.contains(normalized, true) ||
                entity.tags.any { it.contains(normalized, true) }
    }

    /**
     * Checks if single-value field matches filter criteria
     */
    fun matchesField(value: String, filter: FieldFilter): Boolean {
        if (filter.isEmpty) return true
        return filter.queries.any { query ->
            when (filter.matchStrategy) {
                MatchStrategy.CONTAINS -> value.contains(query, true)
                MatchStrategy.EXACT -> value.equals(query, true)
                else -> true
            }
        }
    }

    /**
     * Checks if collection field matches filter criteria
     */
    fun matchesCollection(values: List<String>, filter: FieldFilter): Boolean {
        if (filter.isEmpty) return true
        return when (filter.matchStrategy) {
            MatchStrategy.CONTAINS_ANY ->
                filter.queries.any { query -> values.any { it.contains(query, true) } }
            MatchStrategy.EXACT_ANY ->
                filter.queries.any { query -> values.any { it.equals(query, true) } }
            MatchStrategy.CONTAINS_ALL ->
                filter.queries.all { query -> values.any { it.contains(query, true) } }
            MatchStrategy.EXACT_ALL ->
                filter.queries.all { query -> values.any { it.equals(query, true) } }
            else -> matchesField(values.joinToString(), filter)
        }
    }
}