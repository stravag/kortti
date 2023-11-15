package ch.ranil.kortti.web

import ch.ranil.kortti.web.pages.renderMainPage
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRoutes() {
    routing {
        get("/") { call.renderMainPage() }
        staticResources("/static", basePackage = "/static")

        configureCardRoutes()
        configureAdventCalendarRoutes()
    }
}
