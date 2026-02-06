package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.domain.card.CardType
import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/", produces = ["text/html"])
class CardsController(
    private val cardService: CardService,
    private val templates: Templates
) {

    @GetMapping
    fun page(): String {
        return templates.pageCards().render()
    }

    @GetMapping("/cards")
    fun getCards(): String {
        sleep()
        val cards = cardService.getCards()
        return templates.componentCardTable(cards).render()
    }

    @PostMapping("/cards")
    fun createCard(data: CardFormData): String {
        sleep()
        val card = cardService.createCard(data)
        return templates.componentCardRow(card).render()
    }

    @DeleteMapping("/cards/{id}")
    fun deleteCard(@PathVariable id: UUID) {
        cardService.deleteCard(CardId(id))
    }

    private fun sleep() {
        val sleepDuration = (Math.random() * 1000L).toLong()
        Thread.sleep(sleepDuration)
    }

    data class CardFormData(
        val content: String,
        val type: CardType,
    )
}
