package ch.ranil.kortti.web

import ch.ranil.kortti.domain.CardService
import ch.ranil.kortti.web.pages.renderCardPage
import ch.ranil.kortti.web.pages.renderMainPage
import ch.ranil.kortti.web.pages.renderMissingCardPage
import ch.ranil.kortti.web.utils.pathParam
import ch.ranil.kortti.web.utils.respondRedirect303
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val STATIC_PATH = "/static"
const val CARD_ID = "cardId"

fun Application.configureController() {

    val cardService by inject<CardService>()

    routing {
        get("/") { call.renderMainPage() }
        post("/card") {
            val card = cardService.createCard()
            call.respondRedirect303("/card/${card.id.value}")
        }
        get("/card/{$CARD_ID}") {
            when (val card = cardService.findCard(call.pathParam(CARD_ID))) {
                null -> call.renderMissingCardPage()
                else -> call.renderCardPage(card)
            }
        }
        post("/card/{$CARD_ID}") {
            val cardId = call.pathParam(CARD_ID)
            val card = cardService.addEntryToCard(cardId)
            call.renderCardPage(card)
        }
        staticResources(STATIC_PATH, basePackage = STATIC_PATH)
    }
}
