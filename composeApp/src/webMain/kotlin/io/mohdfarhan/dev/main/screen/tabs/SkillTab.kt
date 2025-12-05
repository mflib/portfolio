package io.mohdfarhan.dev.main.screen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.mohdfarhan.dev.data.Skill
import io.mohdfarhan.dev.data.SkillItem
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioSurfaceColor
import io.mohdfarhan.dev.theme.portfolioSurfaceVariantColor

@Composable
fun SkillTab(skill: Skill) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 48.dp, horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = skill.title, style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 36.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            modifier = Modifier.widthIn(max = 768.dp),
            text = skill.subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = portfolioOnSurfaceVariantColor,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(12.dp))
        SkillsGrid(skill)
    }
}


@Composable
fun SkillsGrid(skill: Skill) {
    val columns = 3
    val skills =skill.items
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = Modifier.fillMaxWidth().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(skills.size) { index ->
            SkillCard(skills[index])
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SkillCard(
    skillItem: SkillItem
) {
    var hovered by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.width(130.dp)
            .wrapContentHeight().padding(2.dp).background(
                color = portfolioSurfaceColor, shape = RoundedCornerShape(16.dp)
            ).border(
                width = 1.dp, color = if (hovered) portfolioPrimaryColor.copy(alpha = 0.5f)
                else portfolioSurfaceVariantColor, shape = RoundedCornerShape(16.dp)
            ).padding(24.dp).onPointerEvent(PointerEventType.Enter) { hovered = true }
            .onPointerEvent(PointerEventType.Exit) { hovered = false },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(8.dp))
        AsyncImage(
            colorFilter = ColorFilter.tint(portfolioPrimaryColor),
            modifier = Modifier.size(32.dp),
            model = "${skillItem.icon}",
            contentDescription = skillItem.title
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = skillItem.title, style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = skillItem.description, style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = portfolioOnSurfaceVariantColor,
            )
        )
        Spacer(Modifier.height(8.dp))
    }
}
