package ch.ranil.kortti.web.pages

import ch.ranil.kortti.domain.Card
import ch.ranil.kortti.domain.CardEntry
import ch.ranil.kortti.web.STATIC_PATH
import ch.ranil.kortti.web.utils.htmx.*
import ch.ranil.kortti.web.utils.htmx.HxSwap.AFTER_BEGIN
import ch.ranil.kortti.web.utils.htmx.HxSwap.BEFORE_END
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

suspend fun PipelineContext<Unit, ApplicationCall>.renderCardPage(card: Card) {
    call.respondHtml {
        head { script { src = "$STATIC_PATH/htmx-1.9.8/htmx.min.js" } }
        body {
            hxBoost()
            h1 { +"Card" }
            p { +"${card.id}" }
            button {
                hxPost("/card/${card.id.value}")
                hxTarget("#entries")
                hxSwap(AFTER_BEGIN)
                hxSelect("ul li:first-child")
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
}

suspend fun PipelineContext<Unit, ApplicationCall>.renderMissingCardPage() {
    call.respondHtml {
        head { script { src = "$STATIC_PATH/htmx-1.9.8/htmx.min.js" } }
        body {
            hxBoost()
            h1 { +"Card Not Found" }
        }
    }
}

fun UL.renderCardEntryFragment(cardEntry: CardEntry) {
    li {
        p { +"${cardEntry.dateTime}" }
        p { +cardEntry.text }
    }
}
