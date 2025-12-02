package io.mohdfarhan.dev

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform