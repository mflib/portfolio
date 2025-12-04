package io.mohdfarhan.dev.main.screen.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp

@Composable
fun TimeLineTab() {
    val items = listOf(
        TimelineItem("Dec 1, 2025", "Started project"),
        TimelineItem("Dec 2, 2025", "Added features"),
        TimelineItem("Dec 3, 2025", "Fixed bugs"),
        TimelineItem("Dec 4, 2025", "Launched app")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        itemsIndexed(items) { index, item ->
            TimelineItem(
                date = item.date, description = item.description, isLast = index == items.size - 1
            )
        }
    }
}

data class TimelineItem(val date: String, val description: String)

@Composable
fun TimelineItem(
    date: String, description: String, isLast: Boolean, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left: Date
        Text(
            text = date,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f).padding(end = 24.dp)
        )

        // Center: Bullet + Vertical Line
        Box(modifier = Modifier.width(48.dp)) {
            // Bullet
            Box(
                modifier = Modifier.size(12.dp).align(Alignment.Center).background(
                        color = MaterialTheme.colorScheme.primary, shape = CircleShape
                    )
            )

            // Vertical line (only if not last item)
            if (!isLast) {
                Canvas(
                    modifier = Modifier.fillMaxHeight().width(2.dp).align(Alignment.Center)
                ) {
                    val canvasHeight = size.height
                    val centerX = size.width / 2

                    drawLine(
                        color = Color.Gray,
                        start = Offset(centerX, canvasHeight / 2),
                        end = Offset(centerX, canvasHeight),
                        strokeWidth = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )
                }
            }
        }

        // Right: Description
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f).padding(start = 24.dp)
        )
    }
}