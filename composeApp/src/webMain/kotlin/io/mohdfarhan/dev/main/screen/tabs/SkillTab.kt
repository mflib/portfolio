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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.mohdfarhan.dev.resources.Res
import io.mohdfarhan.dev.resources.browser_code
import io.mohdfarhan.dev.resources.cloud
import io.mohdfarhan.dev.resources.database
import io.mohdfarhan.dev.resources.iphone
import io.mohdfarhan.dev.resources.learn
import io.mohdfarhan.dev.resources.merge
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioSurfaceColor
import io.mohdfarhan.dev.theme.portfolioSurfaceVariantColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SkillTab() {
    Column(
        modifier = Modifier.fillMaxSize().padding(vertical = 48.dp, horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "My Technical Arsenal", style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 36.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )
        )
        Spacer(Modifier.height(12.dp))
        Text(
            modifier = Modifier.widthIn(max = 768.dp),
            text = "A collection of technologies I'm proficient in, constantly learning and adapting to the ever-evolving digital landscape. From front-end frameworks to back-end languages, these are the tools I use to build robust and beautiful applications.",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = portfolioOnSurfaceVariantColor,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(12.dp))
        SkillsGrid()
    }
}

data class SkillItem(
    val icon: DrawableResource, val title: String, val subtitle: String
)

@Composable
fun SkillsGrid() {
    val columns = 3
    val skills = listOf(
        SkillItem(Res.drawable.iphone, "Mobile", "Android, KMP"),
        SkillItem(Res.drawable.browser_code, "Backend", "Java, Ktor"),
        SkillItem(Res.drawable.database, "Database", "Postgresql, MongoDB, DynamoDB"),
        SkillItem(Res.drawable.cloud, "DevOps", "Docker, Docker-Compose"),
        SkillItem(Res.drawable.merge, "Tooling", "Github, Gitlab"),
        SkillItem(Res.drawable.learn, "Leaning", "AI, ML"),
    )
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
        Icon(
            modifier = Modifier.size(32.dp),
            painter = painterResource(skillItem.icon),
            tint = portfolioPrimaryColor,
            contentDescription = skillItem.title
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = skillItem.title, style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 20.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
            )
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = skillItem.subtitle, style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Normal,
                color = portfolioOnSurfaceVariantColor,
            )
        )
        Spacer(Modifier.height(8.dp))
    }
}
