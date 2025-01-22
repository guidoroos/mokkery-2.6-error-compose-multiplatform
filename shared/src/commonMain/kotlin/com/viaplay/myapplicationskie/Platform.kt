package com.viaplay.myapplicationskie

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform