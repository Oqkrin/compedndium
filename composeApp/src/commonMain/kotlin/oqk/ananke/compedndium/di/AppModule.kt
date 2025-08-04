package oqk.ananke.compedndium.di

import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module
import oqk.ananke.compedndium.entity.data.PlaceholderEntityRepository
import oqk.ananke.compedndium.entity.domain.EntityRepository
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListViewModel

val appModule = module {
    single<EntityRepository> { PlaceholderEntityRepository() }
    viewModel { EntityListViewModel(get()) }
}