package io.mohdfarhan.dev.main.screen.tabs

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor

data class CareerExperience(
    val company: String,
    val role: String,
    val startDate: String,
    val endDate: String,
    val responsibilities: String
)

@Composable
fun ExperienceTab() {
    val experiences = listOf(
        CareerExperience("Company A", "Junior Developer", "Jan 2018", "Dec 2019", "React frontend development"),
        CareerExperience("Company B", "Software Engineer", "Jan 2020", "Dec 2021", "Backend API development"),
        CareerExperience("Company C", "Senior Engineer", "Jan 2022", "Present", "Mobile & backend leadership"),
        CareerExperience("Company D", "Lead Engineer", "2023", "Present", "Project architecture & mentoring")
    )
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 48.dp, horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "My Professional Journey", style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 36.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            modifier = Modifier.widthIn(max = 768.dp),
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
        VerticalCareerTimeline(experiences)
    }
}

@Composable
fun VerticalCareerTimeline(experiences: List<CareerExperience>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 48.dp, vertical = 24.dp)
    ) {
        // Vertical line
        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .align(Alignment.Center)
        ) {
            drawLine(
                color = portfolioPrimaryColor,
                start = androidx.compose.ui.geometry.Offset(size.width / 2, 0f),
                end = androidx.compose.ui.geometry.Offset(size.width / 2, size.height),
                strokeWidth = 4.dp.toPx()
            )
        }

        // Timeline items
        Column(modifier = Modifier.fillMaxWidth()) {
            experiences.forEachIndexed { index, exp ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val isLeft = index % 2 == 0

                    if (isLeft) {
                        CareerCard(exp, Alignment.End, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(32.dp))
                        TimelineBullet()
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                        TimelineBullet()
                        Spacer(modifier = Modifier.width(32.dp))
                        CareerCard(exp, Alignment.Start, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}




@Composable
fun TimelineBullet() {
    Box(
        modifier = Modifier.size(16.dp)
            .background(color = portfolioPrimaryColor, shape = CircleShape)
    )
}

@Composable
fun CareerCard(
    exp: CareerExperience,
    alignment: Alignment.Horizontal,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (alignment == Alignment.Start) Alignment.Start else Alignment.End
    ) {
        Text(
            text = "${exp.role} @ ${exp.company}",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
        )
        Text(
            text = "${exp.startDate} - ${exp.endDate}",
            style = MaterialTheme.typography.bodySmall.copy(
                color = portfolioOnSurfaceVariantColor,
                fontSize = 14.sp
            )
        )
        Text(
            text = exp.responsibilities,
            style = MaterialTheme.typography.bodySmall.copy(
                color = portfolioOnSurfaceVariantColor,
                fontSize = 14.sp
            )
        )
    }
}