package oqk.ananke.compedndium

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(900.dp, 700.dp))
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compedndium",
        state = windowState
    ) {
        App()
    }
}