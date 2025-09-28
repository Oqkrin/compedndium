package oqk.ananke.compedndium.entity.presentation.entity_lists.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import oqk.ananke.compedndium.core.data.ScreenPhiUnits
import oqk.ananke.compedndium.entity.domain.Entity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EntityCard(
    entity: Entity,
    modifier: Modifier = Modifier,
    values: ScreenPhiUnits,
    layout: EntityCardLayout
) {

    val (widthLevel, heightLevel) = 2.5f to 1.5f

    Surface(
        modifier = modifier
            .width(values.minUnit(widthLevel)) // Square shape
            .then(
                when (layout) {
                    EntityCardLayout.CARD -> Modifier.height(values.minUnit(heightLevel))
                    EntityCardLayout.SQUARE -> Modifier.aspectRatio(1f)
                }
            ),
        shape = RoundedCornerShape(ScreenPhiUnits.PHICORNERPERCENT),
        shadowElevation = 10.dp,
        color = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Icon
            if (layout == EntityCardLayout.SQUARE) {
                Icon(
                    imageVector = entity.type.icon,
                    contentDescription = entity.type.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                        .align(Alignment.Center),
                    tint = LocalContentColor.current.copy(alpha = .5f)
                )


                var isFavorite by remember { mutableStateOf(entity.isFavorite) }

                IconToggleButton(
                    checked = isFavorite,
                    onCheckedChange = {
                        entity.isFavorite = it
                        isFavorite = it
                    }, //placeholder till database
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(values.calcPadding(values.minUnit(widthLevel)))
                ){
                    if (isFavorite) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "Remove from favorites"
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "Add to favorites"
                        )
                    }
                }

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().align(Alignment.Center),
                verticalArrangement = Arrangement.SpaceAround
            ) {

                // Title
                Box(
                    modifier = Modifier.width(values.minUnit(widthLevel)).height(values.minUnit(heightLevel + 3))
                ) {
                    BasicText(
                        text = entity.title,
                        style = MaterialTheme.typography.displayLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.Center,
                            lineHeight = 0.sp
                        ),
                        modifier = Modifier.fillMaxSize().padding(horizontal = values.calcPadding(values.minUnit(widthLevel)), vertical = values.calcPadding(values.minUnit(heightLevel + 3))),
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 1.sp
                        )
                    )
                }

                if (layout == EntityCardLayout.CARD) {

                    Box(
                        modifier = Modifier.fillMaxWidth().height(values.minUnit(heightLevel + 2)),
                    ) {
                        Icon(
                            imageVector = entity.type.icon,
                            contentDescription = entity.type.name,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Tags
                Box(
                    modifier = Modifier.width(values.minUnit(widthLevel)).height(values.minUnit(heightLevel + 2))
                ) {
                    BasicText(
                        text = entity.tags.joinToString(" #", "#"),
                        modifier = Modifier.fillMaxSize().padding(horizontal = values.calcPadding(values.minUnit(widthLevel)), vertical = values.calcPadding(values.minUnit(heightLevel + 2))),
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.75f),
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Justify,
                            lineHeight = values.minUnit(heightLevel + 2).value.sp/3.5f
                        ),
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 1.sp
                        )
                    )
                }
            }
        }
    }
}

enum class EntityCardLayout {
    CARD, SQUARE
}


