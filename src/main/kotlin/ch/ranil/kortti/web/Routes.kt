package ch.ranil.kortti.web

import ch.ranil.kortti.web.pages.renderMainPage
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.jte.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutes() {
    routing {
        get("/") { call.renderMainPage() }
        get("/sample") {
            val params = mapOf("id" to 1, "name" to "John")
            call.respond(JteContent("sample.kte", params))
        }
        staticResources("/static", basePackage = "/static")

        configureCardRoutes()
        configureAdventCalendarRoutes()
    }
}
