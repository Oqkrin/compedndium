package oqk.ananke.compedndium.entity.data

import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityType
import oqk.ananke.compedndium.entity.domain.filter.EntityFilter
import oqk.ananke.compedndium.entity.domain.filter.EntityFilterPredicates
import oqk.ananke.compedndium.entity.domain.filter.FieldFilter

/**
 * Data access operations for entities
 */
fun interface EntityRepository {
    suspend fun getFilteredEntities(filter: EntityFilter): List<Entity>
}

/**
 * Shared repository infrastructure with optimized filtering
 */
abstract class BaseEntityRepository : EntityRepository {
    protected abstract val allEntities: List<Entity>

    // Indexes for performance optimization
    protected val tagIndex: Map<String, Set<Entity>> by lazy { buildIndex { it.tags } }
    protected val titleIndex: Map<String, Set<Entity>> by lazy { buildIndex { listOf(it.title) } }

    private fun buildIndex(selector: (Entity) -> List<String>): Map<String, Set<Entity>> {
        return allEntities.flatMap { entity ->
            selector(entity).map { it.lowercase() to entity }
        }.groupBy({ it.first }, { it.second }).mapValues { it.value.toSet() }
    }

    final override suspend fun getFilteredEntities(filter: EntityFilter): List<Entity> {
        return when {
            filter.isEmpty -> allEntities
            filter.hasGlobalSearch && !filter.hasFieldFilters -> searchGlobal(filter.globalQuery)
            filter.isTagFilterOnly -> searchByTags(filter.tagsFilter.queries)
            else -> allEntities.filter { matchesFilter(it, filter) }
        }
    }

    private fun searchGlobal(query: String) = allEntities.filter { matchesGlobal(it, query) }

    private fun searchByTags(tags: Set<String>) = tags.flatMap { tag -> tagIndex[tag.lowercase()] ?: emptyList() }

    protected open fun matchesFilter(entity: Entity, filter: EntityFilter) =
        (!filter.hasGlobalSearch || matchesGlobal(entity, filter.globalQuery)) &&
                matchesField(entity.title, filter.titleFilter) &&
                matchesField(entity.type.name, filter.typeFilter) &&
                matchesCollection(entity.tags, filter.tagsFilter)

    private fun matchesGlobal(entity: Entity, query: String) = EntityFilterPredicates.matchesGlobal(entity, query)
    private fun matchesField(value: String, filter: FieldFilter) = EntityFilterPredicates.matchesField(value, filter)
    private fun matchesCollection(values: List<String>, filter: FieldFilter) =
        EntityFilterPredicates.matchesCollection(values, filter)
}

/**
 * In-memory implementation
 */
class InMemoryEntityRepository : BaseEntityRepository() {
    override val allEntities = listOf(
        Entity("1", "Aragorn", listOf("Hero", "Fighter"), EntityType.PC),
        Entity("2", "Gandalf", listOf("Wizard", "Mentor"), EntityType.NPC),
        Entity("3", "Frodo", listOf("Rogue", "Hobbit"), EntityType.PC),
        Entity("4", "Gondor", listOf("Kingdom", "Capital"), EntityType.WORLD),
        Entity("5", "One Ring", listOf("Artifact", "Ring"), EntityType.OBJECT),
        Entity("6", "Fireball", listOf("Magic", "Fire"), EntityType.ABILITIES),
        Entity("7", "Legolas", listOf("Elf", "Archer"), EntityType.PC),
        Entity("8", "Gimli", listOf("Dwarf", "Warrior"), EntityType.NPC),
        Entity("9", "Fangorn", listOf("Forest", "Ancient"), EntityType.WORLD),
        Entity("10", "Sting", listOf("Weapon", "Sword"), EntityType.OBJECT),
        Entity("11", "Cure Wounds", listOf("Healing", "Light"), EntityType.ABILITIES),
        Entity("12", "Sauron", listOf("Dark Lord", "Evil"), EntityType.NPC)
    )
}