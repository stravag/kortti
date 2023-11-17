package ch.ranil.kortti.web

import gg.jte.generated.precompiled.Templates
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRoutes() {

    val templates by inject<Templates>()

    routing {
        get("/") {
            call.respondTemplate { templates.main() }
        }
        staticResources("/static", basePackage = "/static")

        configureCardRoutes()
        configureAdventCalendarRoutes()
    }
}
