package oqk.ananke.compedndium

import androidx.compose.runtime.Composable
import oqk.ananke.compedndium.core.presentation.theme.CompedndiumTheme
import oqk.ananke.compedndium.di.appModule
import oqk.ananke.compedndium.entity.presentation.entity_lists.EntityListScreenRoot
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = { modules(appModule) }) {
        CompedndiumTheme {
            EntityListScreenRoot(onEntityClick = {})
        }
    }
}
