package ch.ranil.kortti.web

import ch.ranil.kortti.domain.card.CardId
import ch.ranil.kortti.domain.card.CardService
import ch.ranil.kortti.domain.card.CardType
import ch.ranil.kortti.templates.Templates
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

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
        sleepAtLeast(3.seconds)
        val cards = cardService.getCards()
        return templates.componentCardsTableBody(cards).render()
    }

    @PostMapping("/cards")
    fun createCard(data: CardFormData): String {
        sleepAtLeast(500.milliseconds)
        cardService.createCard(data)
        val cards = cardService.getCards()
        return templates.componentCardsTableBody(cards).render()
    }

    @DeleteMapping("/cards/{id}")
    fun deleteCard(@PathVariable id: UUID) {
        cardService.deleteCard(CardId(id))
    }

    private fun sleepAtLeast(duration: Duration = 1.seconds) {
        val sleepDuration = (Math.random() * duration.inWholeMilliseconds).toLong()
        Thread.sleep(sleepDuration)
    }

    data class CardFormData(
        val content: String,
        val type: CardType,
    )
}
