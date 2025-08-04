package oqk.ananke.compedndium.entity.domain

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EntityTest {
    @Test
    fun `entity creation with valid data should succeed`() {
        val entity = Entity("1", EntityType.PC, listOf("hero"), "Aragorn")
        
        assertEquals("1", entity.id)
        assertEquals(EntityType.PC, entity.type)
        assertEquals("Aragorn", entity.title)
        assertTrue(entity.tags.contains("hero"))
    }

    @Test
    fun `entity with empty tags should be valid`() {
        val entity = Entity("2", EntityType.NPC, emptyList(), "Gandalf")
        
        assertTrue(entity.tags.isEmpty())
        assertEquals("Gandalf", entity.title)
    }

    @Test
    fun `entity type should have correct icon`() {
        val pcEntity = Entity("1", EntityType.PC, emptyList(), "Hero")
        val npcEntity = Entity("2", EntityType.NPC, emptyList(), "Villain")
        
        assertEquals(EntityType.PC.icon, pcEntity.type.icon)
        assertEquals(EntityType.NPC.icon, npcEntity.type.icon)
    }
}