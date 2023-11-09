package ch.ranil.plugins

import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*
import java.time.LocalDateTime

const val STATIC_PATH = "/static"

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondHtml {
                head { script { src = "$STATIC_PATH/htmx-1.9.8/htmx.min.js" } }
                body {
                    h1 { +"Hello Htmx" }
                    button {
                        hxPost("/ping")
                        +"Ping"
                    }
                    div {

                    }
                }
            }
        }
        post("/ping") {
            call.respondHtml {
                body { p { +"Ping at ${LocalDateTime.now()}" } }
            }
        }
        // Static plugin. Try to access `/static/index.html`
        staticResources(STATIC_PATH, basePackage = STATIC_PATH)
    }
}
