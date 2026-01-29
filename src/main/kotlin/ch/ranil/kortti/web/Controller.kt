package ch.ranil.kortti.web

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
    fun overviewPage(): String {
        val cards = cardService.getCards()
        return templates.pageOverview(cards).render()
    }

    @PostMapping("/cards")
    fun createCard(data: CardFormData): String {
        Thread.sleep(2000)
        val card = cardService.createCard(data)
        return templates.componentCardRow(card).render()
    }

    @DeleteMapping("/cards/{id}")
    fun deleteCard(@PathVariable id: UUID) {
        cardService.deleteCard(CardId(id))
    }
}
