package ch.ranil.kortti.web.pages

import io.ktor.server.application.*
import kotlinx.html.h1

suspend fun ApplicationCall.renderMissingCardPage() {
    renderPage("Card Not Found") {
        h1 { +"Card Not Found" }
    }
}
