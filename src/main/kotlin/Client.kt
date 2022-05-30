package io.migaku

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.cookie
import io.ktor.http.HttpMethod
import io.ktor.util.date.GMTDate
import io.ktor.util.date.Month
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.div
import kotlinx.html.dom.append
import org.w3c.dom.Node

fun main() {
    window.onload = {
        document.body?.sayHello()
        createWebsocket()
    }
}

fun Node.sayHello() {
    append { div { +"Hello from JS" } }
}

@JsExport
fun createWebsocket() {
    GlobalScope.launch {
        val client = HttpClient(
            {
                install(WebSockets) {
                }
            }
        )
        println("Starting websocket")
        client.webSocket(
            method = HttpMethod.Get,
            host = "localhost",
            port = 8080,
            path = "/ws",
            request = {
                cookie(
                    name = "user_name",
                    value = "jetbrains",
                    expires =
                    GMTDate(
                        seconds = 0,
                        minutes = 0,
                        hours = 10,
                        dayOfMonth = 1,
                        month = Month.APRIL,
                        year = 2023
                    )
                )
            }
        ) {}
    }
}

