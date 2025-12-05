package io.mohdfarhan.dev.main.screen.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mohdfarhan.dev.data.Experience
import io.mohdfarhan.dev.data.ExperienceItem
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioSurfaceColor



@Composable
fun ExperienceTimelineScreen(experience: Experience) {
    val experiences =experience.items
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()).padding(vertical = 48.dp, horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "My Professional Journey",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 36.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Tracing my career path through impactful roles and innovative\nprojects that shaped my expertise.",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = portfolioOnSurfaceVariantColor,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(12.dp))
        experiences.forEachIndexed { index, experience ->
            TimelineItem(
                experience = experience, isFirst = index == 0
            )
            if (index < experiences.size - 1) {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun TimelineItem(
    experience: ExperienceItem, isFirst: Boolean
) {
    var rowHeight by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    Row(
        modifier = Modifier.fillMaxWidth().padding(24.dp).onSizeChanged { size ->
            rowHeight = size.height
        }, verticalAlignment = Alignment.Top
    ) {
        if (experience.isRightAligned) {
            // Description card on left
            Box(
                modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd
            ) {
                DescriptionCard(
                    description = experience.description
                )
            }

            Spacer(modifier = Modifier.width(24.dp))

            // Timeline
            TimelineIndicator(
                isFirst = isFirst, height = with(density) { rowHeight.toDp() })

            Spacer(modifier = Modifier.width(24.dp))

            // Role info on right
            RoleInfo(
                isFirst = isFirst, experience = experience, modifier = Modifier.weight(1f)
            )
        } else {
            // Role info on left
            RoleInfo(
                isFirst = isFirst, experience = experience, modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(24.dp))

            // Timeline
            TimelineIndicator(
                isFirst = isFirst, height = with(density) { rowHeight.toDp() })

            Spacer(modifier = Modifier.width(24.dp))

            // Description card on right
            Box(
                modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart
            ) {
                DescriptionCard(
                    description = experience.description
                )
            }
        }
    }
}

@Composable
fun TimelineIndicator(isFirst: Boolean, height: androidx.compose.ui.unit.Dp) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(16.dp)
    ) {
        // Circle
        Box(
            modifier = Modifier.size(16.dp).background(
                color = if (isFirst) portfolioPrimaryColor else portfolioOnSurfaceVariantColor,
                shape = CircleShape
            )
        )

        // Line using Canvas - draws from circle to bottom of row
        if (height > 16.dp) {
            Canvas(
                modifier = Modifier.width(2.dp).height(height - 16.dp)
            ) {
                drawLine(
                    color = if (isFirst) portfolioPrimaryColor else portfolioOnSurfaceVariantColor,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 2.dp.toPx()
                )

            }
        }
    }
}

@Composable
fun RoleInfo(
    isFirst: Boolean, experience: ExperienceItem, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (experience.isRightAligned) Alignment.Start else Alignment.End
    ) {
        // Period
        Text(
            text = experience.period,
            fontSize = 13.sp,
            color = if (isFirst) portfolioPrimaryColor else portfolioOnSurfaceVariantColor,
            fontWeight = FontWeight.SemiBold,
            textAlign = if (experience.isRightAligned) TextAlign.Start else TextAlign.End
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Job Title
        Text(
            text = experience.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            lineHeight = 26.sp,
            textAlign = if (experience.isRightAligned) TextAlign.Start else TextAlign.End
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Company
        Text(
            text = experience.company,
            fontSize = 15.sp,
            color = portfolioOnSurfaceVariantColor,
            fontWeight = FontWeight.Normal,
            textAlign = if (experience.isRightAligned) TextAlign.Start else TextAlign.End
        )
    }
}

@Composable
fun DescriptionCard(
    description: List<String>
) {
    Card(
        modifier = Modifier.widthIn(max = 500.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = portfolioSurfaceColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            description.forEachIndexed { index, point ->
                Row(
                    modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top
                ) {
                    // Bullet point
                    Text(
                        text = "•",
                        fontSize = 14.sp,
                        color = portfolioOnSurfaceVariantColor,
                        modifier = Modifier.padding(end = 8.dp, top = 2.dp)
                    )

                    // Description text
                    Text(
                        text = point,
                        fontSize = 14.sp,
                        color = portfolioOnSurfaceVariantColor,
                        lineHeight = 22.sp,
                        modifier = Modifier.weight(1f)
                    )
                }

                // Add spacing between items except for the last one
                if (index < description.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}
