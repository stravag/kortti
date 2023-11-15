package ch.ranil.kortti.web

import ch.ranil.kortti.domain.CardService
import ch.ranil.kortti.web.pages.renderCardEntryFragment
import ch.ranil.kortti.web.pages.renderCardPage
import ch.ranil.kortti.web.pages.renderMainPage
import ch.ranil.kortti.web.pages.renderMissingCardPage
import ch.ranil.kortti.web.utils.inCtxOf
import ch.ranil.kortti.web.utils.pathParam
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.UL
import org.koin.ktor.ext.inject

const val STATIC_PATH = "/static"
const val CARD_ID = "cardId"

fun Application.configureController() {

    val cardService by inject<CardService>()

    routing {
        get("/") { renderMainPage() }
        post("/card") {
            val card = cardService.createCard()
            call.respondRedirect("/card/${card.id.value}")
        }
        get("/card/{$CARD_ID}") {
            when (val card = cardService.findCard(call.pathParam(CARD_ID))) {
                null -> renderMissingCardPage()
                else -> renderCardPage(card)
            }
        }
        post("/card/{$CARD_ID}") {
            val entry = cardService.addEntryToCard(call.pathParam(CARD_ID))
            call.respondHtml {
                inCtxOf<UL> {
                    renderCardEntryFragment(entry)
                }
            }
        }
        staticResources(STATIC_PATH, basePackage = STATIC_PATH)
    }
}

/*
 - Kadpata (card in sinhala)
 -
 */