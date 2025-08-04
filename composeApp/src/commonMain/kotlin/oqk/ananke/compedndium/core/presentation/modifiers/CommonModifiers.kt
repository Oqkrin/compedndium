package oqk.ananke.compedndium.core.presentation.modifiers

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import oqk.ananke.compedndium.core.presentation.components.ScreenSizeDependentValues

@Composable
fun Modifier.fabStyle(values: ScreenSizeDependentValues) = 
    this.clip(RoundedCornerShape(values.cornerRadius))

@Composable
fun Modifier.entityItemStyle(values: ScreenSizeDependentValues) = 
    this.clip(RoundedCornerShape(values.cornerRadius))