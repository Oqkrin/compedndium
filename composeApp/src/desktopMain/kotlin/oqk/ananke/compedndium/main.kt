package oqk.ananke.compedndium

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
fun main() = application {
    val windowState = rememberWindowState()
    
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compedndium",
        state = windowState
    ) {
        App()
    }
}