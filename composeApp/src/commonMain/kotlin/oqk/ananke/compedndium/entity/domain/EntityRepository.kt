package oqk.ananke.compedndium.entity.domain

import oqk.ananke.compedndium.core.domain.Result

interface EntityRepository {
    suspend fun getEntities(): Result<List<Entity>, EntityError>
    suspend fun searchEntities(query: String): Result<List<Entity>, EntityError>
}