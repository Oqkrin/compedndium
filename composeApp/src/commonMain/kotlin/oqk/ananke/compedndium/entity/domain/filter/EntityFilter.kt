package oqk.ananke.compedndium.entity.domain.filter

/**
 * Defines matching strategies for field comparisons
 */
enum class MatchStrategy {
    CONTAINS, EXACT, CONTAINS_ANY, EXACT_ANY, CONTAINS_ALL, EXACT_ALL
}

/**
 * Field-specific filtering configuration
 */
data class FieldFilter(
    val queries: Set<String> = emptySet(),
    val matchStrategy: MatchStrategy = MatchStrategy.CONTAINS
) {
    val isEmpty: Boolean get() = queries.isEmpty() || queries.all { it.isBlank() }
    val isNotEmpty: Boolean get() = !isEmpty

    fun withQueries(vararg newQueries: String) = copy(
        queries = (queries + newQueries).filterNot { it.isBlank() }.toSet()
    )

    fun withStrategy(newStrategy: MatchStrategy) = copy(matchStrategy = newStrategy)

    companion object {
        val EMPTY = FieldFilter()
    }
}

/**
 * Complete entity filtering configuration
 */
data class EntityFilter(
    val globalQuery: String = "",
    val titleFilter: FieldFilter = FieldFilter.EMPTY,
    val typeFilter: FieldFilter = FieldFilter.EMPTY,
    val tagsFilter: FieldFilter = FieldFilter.EMPTY
) {
    // State properties
    val isEmpty: Boolean get() =
        globalQuery.isBlank() && titleFilter.isEmpty && typeFilter.isEmpty && tagsFilter.isEmpty
    val hasGlobalSearch: Boolean get() = globalQuery.isNotBlank()
    val hasFieldFilters: Boolean get() =
        titleFilter.isNotEmpty || typeFilter.isNotEmpty || tagsFilter.isNotEmpty
    val isTagFilterOnly: Boolean get() =
        tagsFilter.isNotEmpty && !hasGlobalSearch && titleFilter.isEmpty && typeFilter.isEmpty

    // Builder methods
    fun withGlobalQuery(query: String) = copy(globalQuery = query)
    fun withTitleFilter(vararg queries: String) = copy(titleFilter = titleFilter.withQueries(*queries))
    fun withTypeFilter(vararg queries: String) = copy(typeFilter = typeFilter.withQueries(*queries))
    fun withTagFilter(vararg queries: String) = copy(tagsFilter = tagsFilter.withQueries(*queries))
    fun clearFilters() = EntityFilter(globalQuery = globalQuery)
    fun clearQuery() = copy(globalQuery = "")

    companion object {
        fun fromGlobalQuery(query: String) = EntityFilter(globalQuery = query)
    }
}