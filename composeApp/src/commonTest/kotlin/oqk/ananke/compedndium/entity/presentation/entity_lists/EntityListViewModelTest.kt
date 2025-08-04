package oqk.ananke.compedndium.entity.presentation.entity_lists

import kotlinx.coroutines.test.runTest
import oqk.ananke.compedndium.core.domain.Result
import oqk.ananke.compedndium.entity.domain.Entity
import oqk.ananke.compedndium.entity.domain.EntityError
import oqk.ananke.compedndium.entity.domain.EntityRepository
import oqk.ananke.compedndium.entity.domain.EntityType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FakeEntityRepository : EntityRepository {
    var shouldReturnError = false
    private val entities = listOf(
        Entity("1", EntityType.PC, listOf("hero"), "Test Hero"),
        Entity("2", EntityType.NPC, listOf("villain"), "Test Villain")
    )

    override suspend fun getEntities(): Result<List<Entity>, EntityError> {
        return if (shouldReturnError) {
            Result.Error(EntityError.NetworkError)
        } else {
            Result.Success(entities)
        }
    }

    override suspend fun searchEntities(query: String): Result<List<Entity>, EntityError> {
        return if (shouldReturnError) {
            Result.Error(EntityError.NetworkError)
        } else {
            Result.Success(entities.filter { it.title.contains(query, ignoreCase = true) })
        }
    }
}

class EntityListViewModelTest {
    @Test
    fun `initial state should load entities successfully`() = runTest {
        val repository = FakeEntityRepository()
        val viewModel = EntityListViewModel(repository)
        
        // Wait for initial load
        kotlinx.coroutines.delay(100)
        
        val state = viewModel.state.value
        assertEquals(2, state.currentEntities.size)
        assertEquals(null, state.errorMessage)
    }

    @Test
    fun `search action should filter entities`() = runTest {
        val repository = FakeEntityRepository()
        val viewModel = EntityListViewModel(repository)
        
        viewModel.onAction(EntityListAction.OnSearchQueryChange("Hero"))
        kotlinx.coroutines.delay(100)
        
        val state = viewModel.state.value
        assertEquals(1, state.currentEntities.size)
        assertTrue(state.currentEntities.first().title.contains("Hero"))
    }
}