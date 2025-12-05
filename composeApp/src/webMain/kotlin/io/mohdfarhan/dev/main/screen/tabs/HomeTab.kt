package io.mohdfarhan.dev.main.screen.tabs

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.mohdfarhan.dev.data.Hero
import io.mohdfarhan.dev.theme.portfolioBackgroundColor
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioPrimaryLightColor
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun HomeTab(hero: Hero) {
    Row(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 48.dp, horizontal = 48.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column(
            modifier = Modifier.weight(0.7f)
        ) {
            Text(
                text = hero.name, style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 72.sp,
                    lineHeight = 82.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = hero.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = portfolioPrimaryColor
                )
            )
            Spacer(Modifier.height(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 0.dp, top = 0.dp, bottom = 0.dp, end = 50.dp),
                text = hero.aboutMe,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 18.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Normal,
                    color = portfolioOnSurfaceVariantColor,
                    textAlign = TextAlign.Justify
                )
            )
            Spacer(Modifier.height(16.dp))
//            MyWorkButton(onClick = {})
        }
        Spacer(modifier = Modifier.width(24.dp))
        Box(
            modifier = Modifier.weight(0.225f).aspectRatio(1f).clip(CircleShape)
                .border(2.dp, portfolioPrimaryColor, CircleShape), // sharp border
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = "https://lh3.googleusercontent.com/aida-public/AB6AXuCH_5K7lOB4TSlpzEpxQEGGKS7CBANpzFplRhIdZ7iAuz174w4uQYPbPWc8mlogdkt13Hft5Hh_T0TJPBJzZmCl1gGMOEvRE0htEg-erTPp4g2MawqnWx8hsuYcdRXXiUPLnbQNjx0BcwS1TaECSRLkhU3io2JrFcr2GID_vpyXLPbbecPHUNlHgedsDClNaR8Wt2at_wYD6sl9Ec0IQGcwqXx8zYj81sk1gj12L4av2P6Qv-OYZ0PY9e7xpzflU-N4qjURzuHp6rI",
                contentDescription = "",
                modifier = Modifier.fillMaxSize().clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun MyWorkButton(
    onClick: () -> Unit, modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered = interactionSource.collectIsHoveredAsState()

    val backgroundColor by animateColorAsState(
        targetValue = if (isHovered.value) portfolioPrimaryLightColor else portfolioPrimaryColor,
        label = "button-color"
    )

    val shadowColor by animateColorAsState(
        targetValue = if (isHovered.value) portfolioPrimaryColor.copy(alpha = 0.6f)
        else portfolioPrimaryColor.copy(alpha = 0.4f), label = "shadow-color"
    )
    Button(
        onClick = onClick, modifier = modifier.height(48.dp)                          // h-12
            .wrapContentWidth()    // min-w + max-w
            .shadow(
                elevation = 20.dp, spotColor = shadowColor, ambientColor = shadowColor
            ), interactionSource = interactionSource, colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = portfolioBackgroundColor   // text-background
        ), shape = RoundedCornerShape(16.dp),           // rounded-xl
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth(),
        ) {
            Text(
                "View My Work", fontSize = 16.sp,                     // text-base
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))   // gap-2

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null
            )
        }
    }
}

