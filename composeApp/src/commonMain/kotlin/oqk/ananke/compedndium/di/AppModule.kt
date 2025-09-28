package oqk.ananke.compedndium.di


import org.koin.dsl.module
import oqk.ananke.compedndium.entity.data.EntityRepository
import oqk.ananke.compedndium.entity.data.InMemoryEntityRepository
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListViewModel
import org.koin.core.module.dsl.viewModel

val appModule = module {
    single<EntityRepository> { InMemoryEntityRepository() }
    viewModel { EntityListViewModel(get()) }
}