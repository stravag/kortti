package ch.ranil.kortti.web

import gg.jte.generated.precompiled.Templates
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import org.koin.ktor.ext.inject

fun Application.configureRoutes() {

    val giphyClient by inject<GiphyClient>()
    val templates by inject<Templates>()

    routing {
        get("/") {
            call.respondTemplate { templates.main() }
        }
        post("/giphy-search") {
            val query = call.receiveParameters().getOrFail("search")
            val giphyGifs = giphyClient.getGifs(query)
            call.respondTemplate { templates.giphyResult(giphyGifs) }
        }
        staticResources("/static", basePackage = "/static")

        configureCardRoutes()
        configureAdventCalendarRoutes()
    }
}
