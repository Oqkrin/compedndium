package oqk.ananke.compedndium.entity.domain

import oqk.ananke.compedndium.core.domain.Error

sealed interface EntityError : Error {
    data object NetworkError : EntityError
    data object NotFound : EntityError
    data class Unknown(val message: String) : EntityError
}