package io.migaku

import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.http.HttpMethod
import io.ktor.client.request.header
import kotlinx.html.div
import kotlinx.html.dom.append
import org.w3c.dom.Node
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
    window.onload = {
        document.body?.sayHello()
        createWebsocket()
    }
}

fun Node.sayHello() {
    append {
        div {
            +"Hello from JS"
        }
    }
}

@JsExport
fun createWebsocket() {
    GlobalScope.launch {
        val client = HttpClient() {
            install(WebSockets)
        }
        println("Starting websocket")
        client.webSocket(
            method = HttpMethod.Get,
            host = "localhost",
            port = 8080,
            path = "/ws",
            request = {
                header(
                    "My-Test-Header",
                    "My-Test-Header-Value"
                )
            }) {
        }

    }
}