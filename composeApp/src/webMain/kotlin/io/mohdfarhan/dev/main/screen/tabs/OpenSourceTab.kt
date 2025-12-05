package io.mohdfarhan.dev.main.screen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NorthEast
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mohdfarhan.dev.data.OpenSource
import io.mohdfarhan.dev.data.OpenSourceItem
import io.mohdfarhan.dev.theme.portfolioNeonGlow
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioSurfaceColor
import io.mohdfarhan.dev.theme.portfolioSurfaceVariantColor


@Composable
fun OpenSourceTab(openSource: OpenSource) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 48.dp, horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = openSource.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 36.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = openSource.subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = portfolioOnSurfaceVariantColor,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        OpenSourceGrid(openSource)
    }
}

@Composable
fun OpenSourceGrid(openSource: OpenSource) {
    val columns = 3
    val items = openSource.items
    val uriHandler = LocalUriHandler.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items.size) { index ->
            OpenSourceCard(items[index], onOpen = { githubLink ->
                uriHandler.openUri(githubLink)
            })
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OpenSourceCard(
    openSourceItem: OpenSourceItem,
    onOpen: (String) -> Unit = {}
) {
    var hovered by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.width(130.dp)
            .wrapContentHeight().padding(2.dp).background(
                color = portfolioSurfaceColor, shape = RoundedCornerShape(16.dp)
            ).border(
                width = 1.dp, color = if (hovered) portfolioPrimaryColor.copy(alpha = 0.5f)
                else portfolioSurfaceVariantColor, shape = RoundedCornerShape(16.dp)
            ).padding(24.dp)
            .onPointerEvent(PointerEventType.Enter) { hovered = true }
            .onPointerEvent(PointerEventType.Exit) { hovered = false },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = openSourceItem.title,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                        )
                    )
                    Text(
                        text = openSourceItem.subtitle,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Normal,
                            color = portfolioOnSurfaceVariantColor,
                        )
                    )
                }

                IconButton(onClick = {
                    onOpen(openSourceItem.githubLink)
                }) {
                    Icon(
                        imageVector = Icons.Default.NorthEast,
                        contentDescription = "Open",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = openSourceItem.description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(Modifier.height(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = portfolioPrimaryColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${openSourceItem.stars}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Normal,
                            color = portfolioOnSurfaceVariantColor,
                        )
                    )
                }
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(
                            portfolioNeonGlow,
                            RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = openSourceItem.tags,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Normal,
                            color = portfolioPrimaryColor,
                        )
                    )
                }
            }
        }
    }
}
