package ch.ranil.htmx.web

import ch.ranil.htmx.domain.AdventCalendarService
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.*
import org.koin.ktor.ext.inject
import java.time.LocalDateTime

const val STATIC_PATH = "/static"

fun Application.configureRouting() {

    val adventCalendarService by inject<AdventCalendarService>()

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
            adventCalendarService.createCalendar()
            call.respondHtml {
                body { p { +"Ping at ${LocalDateTime.now()}" } }
            }
        }
        // Static plugin. Try to access `/static/index.html`
        staticResources(STATIC_PATH, basePackage = STATIC_PATH)
    }
}
