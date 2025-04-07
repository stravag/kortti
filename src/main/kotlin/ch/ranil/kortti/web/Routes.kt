package ch.ranil.kortti.web

private const val CARD_ID = "cardId"
private const val CARD_ENTRY_ID = "cardEntryId"

/*
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
 */