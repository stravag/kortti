package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardEntryId
import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class Controller(
    private val cardService: CardService,
    private val templates: Templates
) {

    @GetMapping("/")
    fun cardOverviewPage(): String {
        val cards = cardService.getCards()
        return templates.pageCardOverview(cards).render()
    }

    @GetMapping("/cards/{cardId}")
    fun cardDetailPage(@PathVariable("cardId") cardId: UUID): String {
        val card = cardService.getCard(CardId(cardId))
        return templates.pageCardDetail(card).render()
    }

    @GetMapping("/card-form")
    fun cardForm(): String {
        return templates.componentCardForm(null).render()
    }

    @PostMapping("/cards")
    fun createCard(@RequestBody data: CardFormData): String {
        val card = cardService.createCard(data)
        return templates.componentCardRow(card).render()
    }

    @PostMapping("/cards/{cardId}/entries")
    fun createCardEntry(@PathVariable("cardId") cardId: UUID): String {
        val card = cardService.addEntryToCard(CardId(cardId))
        return templates.pageCardDetail(card).render()
    }

    @DeleteMapping("/cards/{cardId}/entries/{cardEntryId}")
    fun deleteCardEntry(
        @PathVariable("cardId") cardId: UUID,
        @PathVariable("cardEntryId") cardEntryId: UUID
    ): String {
        val card = cardService.deleteEntryFromCard(CardId(cardId), CardEntryId(cardEntryId))
        return templates.pageCardDetail(card).render()
    }
}