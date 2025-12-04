package io.mohdfarhan.dev.main.screen.tabs


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioSurfaceVariantColor


@Immutable  // Best practice for Compose – improves performance
data class Experience(
    val company: String,
    val role: String,
    val startDate: String,        // e.g., "Jan 2021" or "2021"
    val endDate: String,          // Use "Present" or "Current" for ongoing job
    val responsibilities: List<String>,
    val location: String = "",    // Optional: "San Francisco, CA" or "Remote"
    val website: String = "",     // Optional: company website URL
    val logoUrl: String = ""      // Optional: for future image support
) {
    // Helper to check if this is the current job
    val isCurrent: Boolean
        get() = endDate.equals("Present", ignoreCase = true) || endDate.equals(
            "Current", ignoreCase = true
        )
}

@Composable
fun ExpTab() {
    val experiences = listOf(
        Experience(
            "Stellar Solutions Inc.", "Senior Software Engineer", "2021", "Present", listOf(
                "Led the development of a next-generation decentralized finance platform.",
                "Engineered and optimized smart contracts, resulting in a 40% reduction in gas fees.",
                "Mentored a team of junior developers."
            )
        ), Experience(
            "Quantum Dynamics Corp.", "Mid-Level Software Engineer", "2018", "2021", listOf(
                "Developed and maintained a scalable microservices architecture for a high-traffic e-commerce platform.",
                "Implemented CI/CD pipelines, improving deployment frequency by 200%."
            )
        ), Experience(
            "Nebula Systems", "Junior Software Developer", "2016", "2018", listOf(
                "Contributed to the front-end development of the company’s flagship SaaS product using modern JavaScript frameworks.",
                "Collaborated with the UI/UX team to translate design mockups into responsive, interactive web pages."
            )
        ), Experience(
            "Stellar Solutions Inc.", "Senior Software Engineer", "2021", "Present", listOf(
                "Led the development of a next-generation decentralized finance platform.",
                "Engineered and optimized smart contracts, resulting in a 40% reduction in gas fees.",
                "Mentored a team of junior developers."
            )
        ), Experience(
            "Quantum Dynamics Corp.", "Mid-Level Software Engineer", "2018", "2021", listOf(
                "Developed and maintained a scalable microservices architecture for a high-traffic e-commerce platform.",
                "Implemented CI/CD pipelines, improving deployment frequency by 200%."
            )
        ), Experience(
            "Nebula Systems", "Junior Software Developer", "2016", "2018", listOf(
                "Contributed to the front-end development of the company’s flagship SaaS product using modern JavaScript frameworks.",
                "Collaborated with the UI/UX team to translate design mockups into responsive, interactive web pages."
            )
        )

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
        CareerTimeline(experiences)
    }
}

data class CircleParameters(
    val radius: Dp, val backgroundColor: Color
)

object CircleParametersDefaults {
    private val defaultCircleRadius = 12.dp
    fun circleParameters(
        radius: Dp = defaultCircleRadius, backgroundColor: Color = Cyan
    ) = CircleParameters(radius, backgroundColor)
}

data class LineParameters(
    val strokeWidth: Dp, val brush: Brush
)

object LineParametersDefaults {

    private val defaultStrokeWidth = 3.dp

    fun linearGradient(
        strokeWidth: Dp = 3.dp,
        startColor: Color,
        endColor: Color,
        startY: Float = 0.0f,
        endY: Float = Float.POSITIVE_INFINITY
    ): LineParameters {
        val brush = Brush.verticalGradient(
            colors = listOf(startColor, endColor), startY = startY, endY = endY
        )
        return LineParameters(strokeWidth, brush)
    }
}

@Composable
private fun CareerTimeline(experiences: List<Experience>) {
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val circleParameters = CircleParametersDefaults.circleParameters()
            val lineParameters = LineParametersDefaults.linearGradient(
                startColor = portfolioPrimaryColor,
                endColor = portfolioSurfaceVariantColor
            )
            experiences.forEach { experience ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    // LEFT CONTENT
                    TimelineDateAndCompanyCard(experience, TextAlign.End)

                    // CIRCLE
                    Box(
                        modifier = Modifier.size(circleParameters.radius * 2)     // diameter
                            .drawBehind {
                                val circleRadiusInPx = circleParameters.radius.toPx()
                                drawCircle(
                                    color = circleParameters.backgroundColor,
                                    radius = circleParameters.radius.toPx(),
                                    center = Offset(
                                        circleParameters.radius.toPx(),
                                        circleParameters.radius.toPx()
                                    )
                                )
                                lineParameters?.let {
                                    drawLine(
                                        brush = lineParameters.brush,
                                        start = Offset(x = circleRadiusInPx, y = circleRadiusInPx * 2),
                                        end = Offset(x = circleRadiusInPx, y = this.size.height),
                                        strokeWidth = lineParameters.strokeWidth.toPx()
                                    )
                                }
                            }
                    )

                    // RIGHT CONTENT
                   TimelineCard(experience, TextAlign.End, onMeasuredHeight = {})

                }/*  Row(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Box(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                        Column(
                            modifier = Modifier.align(Alignment.TopCenter)
                        ) {
                            TimelineDot()*//*VerticalDivider(
                                thickness = 4.dp,
                                color = portfolioOnSurfaceColor,
                                modifier = Modifier.height(with(LocalDensity.current) { referenceHeight.toDp() })
                            )*//*
                        }

                        Column(
                            modifier = Modifier.wrapContentSize().align(Alignment.TopCenter)
                        ) {
                            Text(
                                text = "${experience.startDate} - ${experience.endDate}",
                                color = Color(0xFFFFD700),
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )
                            Text(
                                text = experience.role,
                                style = MaterialTheme.typography.titleLarge,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            )
                            Text(
                                text = experience.company,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color(0xFFFFD700),
                                modifier = Modifier.padding(top = 6.dp)
                            )
                        }
                        Card(
                            modifier = Modifier.widthIn(max = 420.dp)
                                .wrapContentHeight()
                                .padding(horizontal = 16.dp)
                                .onSizeChanged { size ->
//                                    onMeasuredHeight(size)
                                }.align(Alignment.TopCenter),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF16213E)),
                            shape = RoundedCornerShape(20.dp),
                            elevation = CardDefaults.cardElevation(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(28.dp)
                            ) {
                                Text(
                                    text = "• ${experience.responsibilities}",
                                    color = Color(0xFFDDDDDD),
                                    style = MaterialTheme.typography.bodyMedium,
                                    lineHeight = 28.sp,
                                    modifier = Modifier.padding(vertical = 3.dp)
                                )
                            }
                        }
                    }
                }*/
            }
        }
    }
}

@Composable
private fun TimelineDateAndCompanyCard(experience: Experience, textAlign: TextAlign) {
    Column(
        modifier = Modifier.size(height = 250.dp, width = 250.dp).padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.End
    ) {
        // Date badge
        Text(
            text = "${experience.startDate} - ${experience.endDate}",
            color = Color(0xFFFFD700),
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Text(
            text = experience.role,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            textAlign = textAlign
        )
        Text(
            text = experience.company,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFFFD700),
            modifier = Modifier.padding(top = 6.dp),
            textAlign = textAlign
        )
    }
}

@Composable
private fun TimelineCard(
    experience: Experience, textAlign: TextAlign, onMeasuredHeight: (IntSize) -> Unit
) {
    Card(
        modifier = Modifier.size(height = 250.dp, width = 250.dp).padding(horizontal = 16.dp)
            .onSizeChanged { size ->
                onMeasuredHeight(size)
            },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF16213E)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
//            experience.responsibilities.forEach { duty ->
            Text(
                text = "• ${experience.responsibilities}",
                color = Color(0xFFDDDDDD),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 28.sp,
                textAlign = textAlign,
                modifier = Modifier.padding(vertical = 3.dp)
            )
//            }
        }
    }
}

@Composable
private fun TimelineDot(
    size: Int = 16, color: Color = portfolioPrimaryColor, innerCircleRatio: Float = 0.6f
) {
    Box(
        modifier = Modifier.size(size.dp).background(color = color, shape = CircleShape)
    ) {
        Box(
            modifier = Modifier.size((size * innerCircleRatio).dp)
                .background(color = portfolioOnSurfaceVariantColor, shape = CircleShape)
                .align(Alignment.Center)
        )
    }
}

/*
* Box(modifier = Modifier.weight(1F)) {
                        TimelineDateAndCompanyCard(experience, textAlign = TextAlign.End)
                    }
                    Box(modifier = Modifier.weight(0.1F)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            TimelineDot()
                            VerticalDivider(
                                thickness = 4.dp,
                                color = portfolioOnSurfaceColor,
                                modifier = Modifier.height(with(LocalDensity.current) { referenceHeight.toDp() })
                            )
                        }
                    }
//                    TimelineDateAndCompanyCard(experience, textAlign = TextAlign.End)
                    Box(modifier = Modifier.weight(1F)) {
                        TimelineCard(
                            experience, textAlign = TextAlign.Center
                        ) { intSize ->
                            referenceHeight = intSize.height
                        }
                    }
*
* */