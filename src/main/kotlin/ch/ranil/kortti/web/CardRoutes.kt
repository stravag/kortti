package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardEntryId
import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.domain.card.CardService
import gg.jte.generated.precompiled.Templates
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

const val CARD_ID = "cardId"
const val CARD_ENTRY_ID = "cardEntryId"

fun Routing.configureCardRoutes() {

    val templates by inject<Templates>()
    val cardService by inject<CardService>()

    post("/cards") {
        val card = cardService.createCard()
        call.respondRedirect303("/cards/${card.id.value}")
    }
    get("/cards/{$CARD_ID}") {
        val card = cardService.findCard(call.idPathParam(CARD_ID))
        call.respondTemplate { templates.card(card) }
    }
    post("/cards/{$CARD_ID}/entries") {
        val card = cardService.addEntryToCard(call.idPathParam(CARD_ID))
        call.respondTemplate { templates.card(card) }
    }
    delete("/cards/{$CARD_ID}/entries/{$CARD_ENTRY_ID}") {
        val cardId = call.idPathParam<CardId>(CARD_ID)
        val cardEntryId = call.idPathParam<CardEntryId>(CARD_ENTRY_ID)
        val card = cardService.deleteEntryFromCard(cardId, cardEntryId)
        call.respondTemplate { templates.card(card) }
    }
}
