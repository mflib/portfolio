package io.mohdfarhan.dev.main

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpSend.Plugin.install
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.Platform
import io.mohdfarhan.dev.data.PortfolioData
import io.mohdfarhan.dev.main.viewmodel.PortfolioViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(
        configure = {
            initKoin()
        }
    ) {
        App()
    }
}
fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
       dependencyModules
    )
}
val dependencyModules= module {
    single<HttpClientEngine>{createHttpEngine}
    single<PortfolioViewModel>{PortfolioViewModel()}
}
val createHttpEngine= CIO.create()
