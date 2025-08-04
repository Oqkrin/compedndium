package oqk.ananke.compedndium.entity.data

import oqk.ananke.compedndium.core.domain.Result
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityError
import oqk.ananke.compedndium.entity.domain.EntityRepository
import oqk.ananke.compedndium.entity.domain.EntityType

class PlaceholderEntityRepository : EntityRepository {
    private val entities = listOf(
        Entity("1", EntityType.PC, listOf("Hero", "Fighter"), "Aragorn"),
        Entity("2", EntityType.NPC, listOf("Wizard", "Mentor"), "Gandalf"),
        Entity("3", EntityType.PC, listOf("Rogue", "Hobbit"), "Frodo"),
        Entity("4", EntityType.WORLD, listOf("Kingdom", "Capital"), "Gondor"),
        Entity("5", EntityType.OBJECT, listOf("Artifact", "Ring"), "One Ring"),
        Entity("6", EntityType.ABILITIES, listOf("Magic", "Fire"), "Fireball"),
        Entity("7", EntityType.PC, listOf("Elf", "Archer"), "Legolas"),
        Entity("8", EntityType.NPC, listOf("Dwarf", "Warrior"), "Gimli"),
        Entity("9", EntityType.WORLD, listOf("Forest", "Ancient"), "Fangorn"),
        Entity("10", EntityType.OBJECT, listOf("Weapon", "Sword"), "Sting"),
        Entity("11", EntityType.ABILITIES, listOf("Healing", "Light"), "Cure Wounds"),
        Entity("12", EntityType.NPC, listOf("Dark Lord", "Evil"), "Sauron")
    )
    
    override suspend fun getEntities(): Result<List<Entity>, EntityError> = 
        Result.Success(entities)
    
    override suspend fun searchEntities(query: String): Result<List<Entity>, EntityError> = 
        Result.Success(
            if (query.isBlank()) entities 
            else entities.filter { 
                it.title.contains(query, ignoreCase = true) || 
                it.tags.any { tag -> tag.contains(query, ignoreCase = true) }
            }
        )
}