package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.web.pages.renderCardPage
import ch.ranil.kortti.web.pages.renderMissingCardPage
import ch.ranil.kortti.web.utils.cardEntryIdPathParam
import ch.ranil.kortti.web.utils.cardIdPathParam
import ch.ranil.kortti.web.utils.respondRedirect303
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val CARD_ID = "cardId"
const val CARD_ENTRY_ID = "cardEntryId"

fun Routing.configureCardRoutes() {

    val cardService by inject<CardService>()

    post("/cards") {
        val card = cardService.createCard()
        call.respondRedirect303("/cards/${card.id.value}")
    }
    get("/cards/{$CARD_ID}") {
        when (val card = cardService.findCard(call.cardIdPathParam(CARD_ID))) {
            null -> call.renderMissingCardPage()
            else -> call.renderCardPage(card)
        }
    }
    post("/cards/{$CARD_ID}/entries") {
        val cardId = call.cardIdPathParam(CARD_ID)
        val card = cardService.addEntryToCard(cardId)
        call.renderCardPage(card)
    }
    delete("/cards/{$CARD_ID}/entries/{$CARD_ENTRY_ID}") {
        val cardId = call.cardIdPathParam(CARD_ID)
        val cardEntryId = call.cardEntryIdPathParam(CARD_ENTRY_ID)
        val card = cardService.deleteEntryFromCard(cardId, cardEntryId)
        call.renderCardPage(card)
    }
}
