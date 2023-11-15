package ch.ranil.kortti.web.pages

import ch.ranil.kortti.domain.Card
import ch.ranil.kortti.domain.CardEntry
import ch.ranil.kortti.web.utils.htmx.HxSwap
import ch.ranil.kortti.web.utils.htmx.HxSwap.AFTER_BEGIN
import ch.ranil.kortti.web.utils.htmx.hxPost
import ch.ranil.kortti.web.utils.htmx.hxSwap
import ch.ranil.kortti.web.utils.htmx.hxTarget
import io.ktor.server.application.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

suspend fun PipelineContext<Unit, ApplicationCall>.renderCardPage(card: Card) {
    renderPage(card.id.value.toString()) {
        h1 { +"Card" }
        p { +"${card.id}" }
        button {
            hxPost("/card/${card.id.value}")
            hxTarget("#entries")
            hxSwap(AFTER_BEGIN)
            +"Add entry"
        }
        ul {
            id = "entries"
            card.entries
                .sortedByDescending { it.dateTime }
                .forEach {
                    renderCardEntryFragment(it)
                }
        }
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.renderMissingCardPage() {
    renderPage("Card Not Found") {
        h1 { +"Card Not Found" }
    }
}

fun UL.renderCardEntryFragment(cardEntry: CardEntry) {
    li {
        p { +"${cardEntry.dateTime}" }
        p { +cardEntry.text }
    }
}
