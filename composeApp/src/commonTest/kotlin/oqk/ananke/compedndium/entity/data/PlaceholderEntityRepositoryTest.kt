package oqk.ananke.compedndium.entity.data

import kotlinx.coroutines.test.runTest
import oqk.ananke.compedndium.core.domain.Result
import kotlin.test.Test
import kotlin.test.assertTrue

class PlaceholderEntityRepositoryTest {
    private val repository = PlaceholderEntityRepository()

    @Test
    fun `getEntities should return success with non-empty list`() = runTest {
        val result = repository.getEntities()
        
        assertTrue(result is Result.Success)
        assertTrue(result.data.isNotEmpty())
    }

    @Test
    fun `searchEntities with empty query should return all entities`() = runTest {
        val allEntities = repository.getEntities()
        val searchResult = repository.searchEntities("")
        
        assertTrue(allEntities is Result.Success)
        assertTrue(searchResult is Result.Success)
        assertTrue(allEntities.data.size == searchResult.data.size)
    }

    @Test
    fun `searchEntities should filter by title`() = runTest {
        val result = repository.searchEntities("Aragorn")
        
        assertTrue(result is Result.Success)
        assertTrue(result.data.any { it.title.contains("Aragorn") })
    }
}