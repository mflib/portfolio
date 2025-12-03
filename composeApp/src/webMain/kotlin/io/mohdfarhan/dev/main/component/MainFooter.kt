package io.mohdfarhan.dev.main.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import io.mohdfarhan.dev.resources.Res
import io.mohdfarhan.dev.resources.instagram
import io.mohdfarhan.dev.resources.linkedin
import io.mohdfarhan.dev.resources.twitter
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioSurfaceVariantColor
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainFooter() {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(
            horizontal = 16.dp, vertical = 8.dp
        )
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            thickness = 0.5.dp,
            color = portfolioSurfaceVariantColor
        )
        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start) {
                Text(
                    text = "© 2025 Mohammed Farhan — All Rights Reserved",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Based in India", style = MaterialTheme.typography.labelMedium.copy(
                        color = portfolioOnSurfaceVariantColor
                    )
                )
            }
            Column(
                modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Built with Compose Multiplatform",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.White
                    )
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Kotlin/Wasm",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = portfolioOnSurfaceVariantColor
                    ),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        uriHandler.openUri("https://kotlinlang.org/docs/wasm-overview.html")
                    })
            }

            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.End) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.linkedin),
                        contentDescription = "LinkedIn",
                        modifier = Modifier.size(32.dp).clickable {
                            uriHandler.openUri("https://kotlinlang.org/docs/wasm-overview.html")

                        })
                    Icon(
                        painter = painterResource(Res.drawable.twitter),
                        contentDescription = "Twitter",
                        modifier = Modifier.size(32.dp).clickable {
                            uriHandler.openUri("https://kotlinlang.org/docs/wasm-overview.html")
                        }
                    )
                    Icon(
                        painter = painterResource(Res.drawable.instagram),
                        contentDescription = "Instagram",
                        modifier = Modifier.size(32.dp).clickable {
                            uriHandler.openUri("https://kotlinlang.org/docs/wasm-overview.html")
                        })
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Credits- Flat Icon", style = MaterialTheme.typography.labelMedium.copy(
                        color = portfolioOnSurfaceVariantColor
                    )
                )
            }
        }
    }
}