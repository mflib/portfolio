package io.mohdfarhan.dev.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.mohdfarhan.dev.main.enums.Tabs
import io.mohdfarhan.dev.main.screen.tabs.ExpTab
import io.mohdfarhan.dev.main.screen.tabs.ExperienceTab
import io.mohdfarhan.dev.main.screen.tabs.HomeTab
import io.mohdfarhan.dev.main.screen.tabs.SkillTab
import io.mohdfarhan.dev.theme.portfolioBackgroundColor
import io.mohdfarhan.dev.theme.portfolioNeonGlow
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioSurfaceVariantColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    isDarkTheme: Boolean, onThemeToggled: () -> Unit
) {
    val scope = rememberCoroutineScope()
    rememberPagerState(pageCount = { Tabs.entries.size })
    val selectedTabIndex = remember { mutableStateOf(0) }
    Scaffold(topBar = {
        HeaderStyledTopAppBar(
            isDarkTheme = isDarkTheme,
            selectedTabIndex = selectedTabIndex.value,
            onThemeToggled = onThemeToggled,
        ) { index ->
            scope.launch {
                selectedTabIndex.value = index
            }
        }
    }, floatingActionButton = {}, bottomBar = {}) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize()
                .background(portfolioBackgroundColor)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier.weight(1f).fillMaxWidth()
                ) {
                    when (Tabs.entries[selectedTabIndex.value]) {
                        Tabs.Home -> {
                            HomeTab()
                        }

                        Tabs.Skills -> {
                            SkillTab()
                        }

                        Tabs.Projects -> {}
                        Tabs.Experience -> {
                            ExpTab()
                        }
                        Tabs.OpenSource -> {}
                        Tabs.Contact -> {}
                        Tabs.Resume -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderStyledTopAppBar(
    isDarkTheme: Boolean,
    selectedTabIndex: Int,
    onThemeToggled: () -> Unit,
    onTabSelected: (Int) -> Unit
) {
    Surface(
        color = portfolioBackgroundColor,
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth().height(64.dp).padding(horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "mohdfarhan.dev",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Tabs.entries.forEachIndexed { index, tab ->
                        val isSelected = selectedTabIndex == index
                        Text(
                            text = tab.text,
                            style = MaterialTheme.typography.titleMedium,
                            color = if (isSelected) portfolioPrimaryColor
                            else portfolioOnSurfaceVariantColor,
                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                            modifier = Modifier.clip(RoundedCornerShape(8.dp))
                                .clickable { onTabSelected(index) }
                                .padding(horizontal = 12.dp, vertical = 8.dp).hoverable(
                                    enabled = true,
                                    interactionSource = remember { MutableInteractionSource() })
                                .then(
                                    if (isSelected) {
                                        Modifier.background(
                                            color = portfolioNeonGlow.copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(8.dp)
                                        ).padding(vertical = 8.dp, horizontal = 8.dp)
                                    } else Modifier
                                )
                        )
                    }
                    IconButton(
                        onClick = { onThemeToggled() },
                        modifier = Modifier.size(38.dp).clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                    ) {
                        Icon(
                            imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle theme",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                thickness = 0.5.dp,
                color = portfolioSurfaceVariantColor
            )
        }
    }
}

