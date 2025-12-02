package io.mohdfarhan.dev.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import io.mohdfarhan.dev.main.component.MainScaffold
import io.mohdfarhan.dev.theme.PortfolioTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val isDarkTheme = remember { mutableStateOf(true) }
    PortfolioTheme(darkTheme = isDarkTheme.value) {
        MainScaffold(
            isDarkTheme = isDarkTheme.value,
            onThemeToggled = {
            isDarkTheme.value = !isDarkTheme.value
        })
    }
}