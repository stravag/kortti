package ch.ranil.kortti.web.pages

import ch.ranil.kortti.domain.Card
import ch.ranil.kortti.domain.CardEntry
import ch.ranil.kortti.domain.CardId
import ch.ranil.kortti.web.utils.htmx.*
import ch.ranil.kortti.web.utils.htmx.HxSwap.AFTER_BEGIN
import io.ktor.server.application.*
import io.ktor.util.pipeline.*
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
                    renderCardEntryFragment(card.id, entry)
                }
        }
    }
}

suspend fun ApplicationCall.renderMissingCardPage() {
    renderPage("Card Not Found") {
        h1 { +"Card Not Found" }
    }
}

fun UL.renderCardEntryFragment(cardId: CardId, cardEntry: CardEntry) {
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
