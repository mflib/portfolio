package io.mohdfarhan.dev.main.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import io.mohdfarhan.dev.data.PortfolioData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.js.js

class PortfolioViewModel(
): ViewModel() {
    val TAG="PortfolioViewModel"


    private val client =HttpClient {
        engine {  }
        install(ContentNegotiation){
            json()
        }
    }

    private val internalPortfolioData= MutableStateFlow<PortfolioData?>(null)
    val portfolioData=internalPortfolioData
    init {
        viewModelScope.launch {
            val data=fetchPortfolioData()
            delay(100)
            internalPortfolioData.value=data
        }
    }

    suspend fun fetchPortfolioData(): PortfolioData? {
        return try {
            val data: PortfolioData = client
                .get("https://pub-ab8a763d2da24ec4baf24ad77efef1bd.r2.dev/portfolio.json")
                .body()
            println("Fetched portfolio: ${data.footer}")
            data
        } catch (e: Exception) {
            println("Fetch failed: ${e.message}")
            null
        }
    }
    override fun onCleared() {
        super.onCleared()
    }
}