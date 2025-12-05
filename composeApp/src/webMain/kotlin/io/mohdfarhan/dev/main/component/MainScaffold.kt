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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import io.mohdfarhan.dev.main.enums.Tabs
import io.mohdfarhan.dev.main.screen.tabs.ExperienceTimelineScreen
import io.mohdfarhan.dev.main.screen.tabs.HomeTab
import io.mohdfarhan.dev.main.screen.tabs.OpenSourceTab
import io.mohdfarhan.dev.main.screen.tabs.SkillTab
import io.mohdfarhan.dev.main.viewmodel.PortfolioViewModel
import io.mohdfarhan.dev.theme.portfolioBackgroundColor
import io.mohdfarhan.dev.theme.portfolioNeonGlow
import io.mohdfarhan.dev.theme.portfolioOnSurfaceVariantColor
import io.mohdfarhan.dev.theme.portfolioPrimaryColor
import io.mohdfarhan.dev.theme.portfolioSurfaceVariantColor
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    portfolioViewModel: PortfolioViewModel,
    isDarkTheme: Boolean, onThemeToggled: () -> Unit
) {
    val portfolioData by portfolioViewModel.portfolioData.collectAsState()
    val scope = rememberCoroutineScope()
    val selectedTabIndex = remember { mutableStateOf(0) }
    val error = remember { mutableStateOf<String?>(null) }

    if (portfolioData == null) {
        Box(
            modifier = Modifier.fillMaxSize().background(portfolioBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                strokeWidth = 3.dp,
                strokeCap = StrokeCap.Butt,
                modifier = Modifier.size(52.dp),
                color = portfolioPrimaryColor,
                trackColor = portfolioNeonGlow
            )
        }
    } else {
        Scaffold(topBar = {
            HeaderStyledTopAppBar(
                isDarkTheme = isDarkTheme,
                selectedTabIndex = selectedTabIndex.value,
                onThemeToggled = onThemeToggled,
            ) { index ->
                scope.launch {
                    selectedTabIndex.value = index
                    error.value = "wefewv"
                }
            }
        }, floatingActionButton = {}, bottomBar = {})
        { paddingValues ->
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
                                portfolioData?.hero?.let {
                                    HomeTab(it)
                                }
                            }

                            Tabs.Skills -> {
                                portfolioData?.skill?.let {
                                    SkillTab(it)
                                }
                            }

                            Tabs.Experience -> {
                                portfolioData?.experience?.let {
                                    ExperienceTimelineScreen(it)

                                }
                            }

                            Tabs.OpenSource -> {
                                portfolioData?.openSource?.let {
                                    OpenSourceTab(it)
                                }
                            }
                        }
                    }
                    portfolioData?.footer?.let {
                        MainFooter(it)
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
                    text = buildAnnotatedString {
                        // "mohdfarhan" part
                        withStyle(
                            style = SpanStyle(
                                color = portfolioPrimaryColor,
                                fontWeight = FontWeight.Bold,
                            )
                        ) {
                            append("mohdfarhan")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = portfolioNeonGlow,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append(".dev")
                        }
                    },
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.clickable {
                        onTabSelected(0)
                    }
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

