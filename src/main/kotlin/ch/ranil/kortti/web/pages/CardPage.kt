package ch.ranil.kortti.web.pages

import ch.ranil.kortti.domain.card.Card
import ch.ranil.kortti.domain.card.CardEntry
import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.web.utils.htmx.hxDelete
import ch.ranil.kortti.web.utils.htmx.hxPost
import ch.ranil.kortti.web.utils.htmx.hxTarget
import io.ktor.server.application.*
import kotlinx.html.*

suspend fun ApplicationCall.renderCardPage(card: Card) {
    renderPage(card.id.value.toString()) {
        h1 { +"Card" }
        p { +"${card.id}" }
        button {
            hxPost("/cards/${card.id.value}/entries")
            hxTarget("body")
            +"Add entry"
        }
        ul {
            id = "entries"
            card.entries
                .sortedByDescending { it.dateTime }
                .forEach { entry ->
                    renderCardEntry(card.id, entry)
                }
        }
    }
}

suspend fun ApplicationCall.renderMissingCardPage() {
    renderPage("Card Not Found") {
        h1 { +"Card Not Found" }
    }
}

fun UL.renderCardEntry(cardId: CardId, cardEntry: CardEntry) {
    li {
        p { +"${cardEntry.dateTime}" }
        p { +cardEntry.text }
        button {
            hxDelete("/cards/${cardId.value}/entries/${cardEntry.id.value}")
            hxTarget("body")
            +"Delete"
        }
    }
}
