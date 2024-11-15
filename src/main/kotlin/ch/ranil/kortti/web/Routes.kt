package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardEntryId
import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.domain.card.CardService
import gg.jte.generated.precompiled.Templates
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.util.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

private const val CARD_ID = "cardId"
private const val CARD_ENTRY_ID = "cardEntryId"

fun Application.configureRoutes() {

    val giphyClient by inject<GiphyClient>()
    val templates by inject<Templates>()
    val cardService by inject<CardService>()

    routing {
        staticResources("/static", basePackage = "/static")

        get("/") {
            val cards = cardService.getCards()
            call.respondTemplate { templates.pageCardOverview(cards) }
        }

        post("/giphy-search") {
            val parameters = call.receiveParameters()
            val query = parameters.getOrFail("search")
            val triggerUrl = parameters.getOrFail("triggerUrl")
            if (query.isNotEmpty()) {
                val giphyGifs = giphyClient.getGifs(query)
                call.respondTemplate { templates.componentGiphyResult(giphyGifs, triggerUrl) }
            } else {
                call.respondTemplate { templates.blank() }
            }
        }

        post("/cards") {
            val data = call.receive<CardFormData>()
            val card = cardService.createCard(data)
            call.respondTemplate { templates.componentCardRow(card) }
        }

        get("/cards/{$CARD_ID}") {
            val card = cardService.getCard(call.idPathParam<CardId>(CARD_ID))
            call.respondTemplate { templates.pageCardDetail(card) }
        }

        get("/card-form") {
            call.respondTemplate { templates.componentCardForm(null) }
        }

        post("/cards/{$CARD_ID}/entries") {
            val card = cardService.addEntryToCard(call.idPathParam<CardId>(CARD_ID))
            call.respondTemplate { templates.pageCardDetail(card) }
        }

        delete("/cards/{$CARD_ID}/entries/{$CARD_ENTRY_ID}") {
            val cardId = call.idPathParam<CardId>(CARD_ID)
            val cardEntryId = call.idPathParam<CardEntryId>(CARD_ENTRY_ID)
            val card = cardService.deleteEntryFromCard(cardId, cardEntryId)
            call.respondTemplate { templates.pageCardDetail(card) }
        }
    }
}

@Serializable
data class CardFormData(
    val name: String
)